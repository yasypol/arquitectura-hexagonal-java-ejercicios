package com.astrobookings.business;

import java.time.LocalDateTime;
import java.util.List;

import com.astrobookings.persistence.FlightRepository;
import com.astrobookings.persistence.RocketRepository;
import com.astrobookings.persistence.models.Flight;
import com.astrobookings.persistence.models.FlightStatus;

public class FlightService {
  private final FlightRepository flightRepository;
  private final RocketRepository rocketRepository;

  public FlightService(FlightRepository flightRepository, RocketRepository rocketRepository) {
    this.flightRepository = flightRepository;
    this.rocketRepository = rocketRepository;
  }

  public List<Flight> getFlights(String statusFilter) {
    if (statusFilter != null && !statusFilter.isEmpty()) {
      return flightRepository.findByStatus(statusFilter);
    } else {
      return flightRepository.findAll();
    }
  }

  public Flight createFlight(Flight flight) {
    // Set defaults
    flight.setStatus(FlightStatus.SCHEDULED);
    flight.setMinPassengers(5);

    // Validate
    String error = validateFlight(flight);
    if (error != null) {
      throw new IllegalArgumentException(error);
    }

    // Save
    return flightRepository.save(flight);
  }

  private String validateFlight(Flight flight) {
    // Input structure validations
    if (flight.getRocketId() == null || flight.getRocketId().trim().isEmpty()) {
      return "Rocket ID must be provided";
    }
    if (flight.getDepartureDate() == null) {
      return "Departure date must be provided";
    }
    if (flight.getBasePrice() <= 0) {
      return "Base price must be positive";
    }

    // Business validations
    if (rocketRepository.findAll().stream().noneMatch(r -> r.getId().equals(flight.getRocketId()))) {
      return "Rocket with id " + flight.getRocketId() + " does not exist";
    }

    LocalDateTime now = LocalDateTime.now();
    if (!flight.getDepartureDate().isAfter(now)) {
      return "Departure date must be in the future";
    }

    LocalDateTime oneYearAhead = now.plusYears(1);
    if (flight.getDepartureDate().isAfter(oneYearAhead)) {
      return "Departure date cannot be more than 1 year ahead";
    }

    return null;
  }
}