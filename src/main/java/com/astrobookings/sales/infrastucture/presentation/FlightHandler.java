package com.astrobookings.sales.infrastucture.presentation;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.astrobookings.sales.domain.models.entities.Flight;
import com.astrobookings.sales.domain.ports.input.FlightUseCases;
import com.astrobookings.shared.infrastucture.presentation.BaseHandler;
import com.sun.net.httpserver.HttpExchange;

public class FlightHandler extends BaseHandler {
  
  private final FlightUseCases flightUseCases;

  public FlightHandler(FlightUseCases flightUseCases) {
    this.flightUseCases = flightUseCases;
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    String method = exchange.getRequestMethod();

    if ("GET".equals(method)) {
      handleGet(exchange);
    } else if ("POST".equals(method)) {
      handlePost(exchange);
    } else {
      this.handleMethodNotAllowed(exchange);
    }
  }

  private void handleGet(HttpExchange exchange) throws IOException {
    String response = "";
    int statusCode = 200;

    try {
      URI uri = exchange.getRequestURI();
      String query = uri.getQuery();
      String statusFilter = null;
      if (query != null) {
        Map<String, String> params = this.parseQuery(query);
        statusFilter = params.get("status");
      }

      response = this.objectMapper.writeValueAsString(flightUseCases.getFlights(statusFilter));
    } catch (Exception e) {
      statusCode = 500;
      response = "{\"error\": \"Internal server error\"}";
    }

    sendResponse(exchange, statusCode, response);
  }

  private void handlePost(HttpExchange exchange) throws IOException {
    String response = "";
    int statusCode = 201;

    try {
      // Parse JSON body
      InputStream is = exchange.getRequestBody();
      String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
      Flight flight = this.objectMapper.readValue(body, Flight.class);

      Flight saved = flightUseCases.createFlight(flight);
      response = this.objectMapper.writeValueAsString(saved);
    } catch (IllegalArgumentException e) {
      String error = e.getMessage();
      if (error.contains("does not exist")) {
        statusCode = 404;
      } else {
        statusCode = 400;
      }
      response = "{\"error\": \"" + error + "\"}";
    } catch (Exception e) {
      statusCode = 400;
      response = "{\"error\": \"Invalid JSON or request\"}";
    }

    sendResponse(exchange, statusCode, response);
  }

  protected Map<String, String> parseQuery(String query) {
    Map<String, String> params = new HashMap<>();
    if (query != null) {
      String[] pairs = query.split("&");
      for (String pair : pairs) {
        String[] keyValue = pair.split("=");
        if (keyValue.length == 2) {
          params.put(keyValue[0], keyValue[1]);
        }
      }
    }
    return params;
  }
}