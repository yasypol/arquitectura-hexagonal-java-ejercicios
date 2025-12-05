package com.astrobookings;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.astrobookings.fleet.infrastucture.presentation.RocketHandler;
import com.astrobookings.sales.infrastucture.presentation.AdminHandler;
import com.astrobookings.sales.infrastucture.presentation.BookingHandler;
import com.astrobookings.sales.infrastucture.presentation.FlightHandler;
import com.sun.net.httpserver.HttpServer;


public class AstroBookingsApp {
  public static void main(String[] args) throws IOException {
    // Create HTTP server on port 8080
    HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

    // Register handlers for endpoints
    server.createContext("/rockets", new RocketHandler(Config.rocketUseCase));
    server.createContext("/flights", new FlightHandler(Config.flightUseCase));
    server.createContext("/bookings", new BookingHandler(Config.bookingUseCase));
    server.createContext("/admin/cancel-flights", new AdminHandler(Config.cancellationUseCases));

    // Start server
    server.setExecutor(null); // Use default executor
    server.start();
    System.out.println("Server started at http://localhost:8080");
  }
}
