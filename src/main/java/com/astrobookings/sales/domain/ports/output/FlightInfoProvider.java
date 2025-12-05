package com.astrobookings.sales.domain.ports.output;


public interface FlightInfoProvider {

    FlightInfo getFlightById(String id);

    record FlightInfo(
      String id,
      String rocketId,
      java.time.LocalDateTime departureDate,
      double basePrice,
      String status,
      int minPassengers,
      int capacity
  ) {}
}
