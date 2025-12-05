package com.astrobookings.fleet.infrastucture.presentation;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.astrobookings.fleet.domain.models.CreateRocketCommand;
import com.astrobookings.fleet.domain.ports.input.RocketsUseCases;
import com.astrobookings.shared.infrastucture.presentation.BaseHandler;
import com.sun.net.httpserver.HttpExchange;

public class RocketHandler extends BaseHandler {
  private final RocketsUseCases rocketUseCases;
  private HttpExchange exchange;

  public RocketHandler(RocketsUseCases rocketUseCases) {
    this.rocketUseCases = rocketUseCases;
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    
    String method = exchange.getRequestMethod();
    this.exchange = exchange;

    if ("GET".equals(method)) {
      getAllRockets();
    } else if ("POST".equals(method)) {
      saveRocket();
    } else {
      this.handleMethodNotAllowed(exchange);
    }
  }

  public void getAllRockets() throws IOException {
    String response = "";
    int statusCode = 200;

    try {
      response = this.objectMapper.writeValueAsString(rocketUseCases.getAllRockets());
    } catch (Exception e) {
      statusCode = 500;
      response = "{\"error\": \"Internal server error\"}";
    }

    sendResponse(exchange, statusCode, response);
  }

  public void saveRocket() throws IOException {
    
    String response = "";
    int statusCode = 200;

    try {
      // Parse JSON body
      InputStream is = exchange.getRequestBody();
      String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
      CreateRocketCommand rocket = this.objectMapper.readValue(body, CreateRocketCommand.class);

      // Business validations mixed with input validation
      String error = validateRocket(rocket);

      if (error != null) {
        statusCode = 400;
        response = "{\"error\": \"" + error + "\"}";
      } else {
        var saved = rocketUseCases.saveRocket(rocket);
        statusCode = 201;
        response = this.objectMapper.writeValueAsString(saved);
      }
    } catch (Exception e) {
      statusCode = 400;
      response = "{\"error\": \"Invalid JSON or request\"}";
    }

    sendResponse(exchange, statusCode, response);
  }

  private String validateRocket(CreateRocketCommand command) {

    if (command.name() == null || command.name().trim().isEmpty()) {
      return "Rocket name must be provided";
    }
    
    return null;
  }

}