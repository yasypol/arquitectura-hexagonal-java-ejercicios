package com.astrobookings.presentation;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.astrobookings.domain.RocketService;
import com.astrobookings.domain.dtos.RocketDto;
import com.astrobookings.presentation.factory.RepositoryAdapterFactory;
import com.astrobookings.domain.ports.RocketServicePort;
import com.sun.net.httpserver.HttpExchange;

public class RocketHandler extends BaseHandler {

  private final RocketServicePort rocketService = new RocketService(RepositoryAdapterFactory.getRocketRepositoryAdapter());

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
      response = this.objectMapper.writeValueAsString(rocketService.getAllRockets());
    } catch (Exception e) {
      statusCode = 500;
      response = "{\"error\": \"Internal server error\"}";
    }

    sendResponse(exchange, statusCode, response);
  }

  private void handlePost(HttpExchange exchange) throws IOException {
    
    String response = "";
    int statusCode = 200;

    try {
      // Parse JSON body
      InputStream is = exchange.getRequestBody();
      String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
      RocketDto rocket = this.objectMapper.readValue(body, RocketDto.class);

      // Business validations mixed with input validation
      String error = validateRocket(rocket);

      if (error != null) {
        statusCode = 400;
        response = "{\"error\": \"" + error + "\"}";
      } else {
        String saved = rocketService.createRocket(rocket);
        statusCode = 201;
        response = this.objectMapper.writeValueAsString(saved);
      }
    } catch (Exception e) {
      statusCode = 400;
      response = "{\"error\": \"Invalid JSON or request\"}";
    }

    sendResponse(exchange, statusCode, response);
  }

  private String validateRocket(RocketDto rocket) {

    if (rocket.name == null || rocket.name.trim().isEmpty()) {
      return "Rocket name must be provided";
    }
    
    return null;
  }

}