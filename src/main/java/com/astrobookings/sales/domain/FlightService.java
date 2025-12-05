package com.astrobookings.sales.domain;

import java.util.List;

import com.astrobookings.fleet.domain.ports.output.RocketRepositoryPort;
import com.astrobookings.sales.domain.models.entities.Flight;
import com.astrobookings.sales.domain.models.vos.FlightStatus;
import com.astrobookings.sales.domain.ports.input.FlightUseCases;
import com.astrobookings.sales.domain.ports.output.FlightRepositoryPort;
import com.astrobookings.shared.domain.models.ValidationException;

public class FlightService implements FlightUseCases{
  
  private final FlightRepositoryPort flightRepository;
  private final RocketRepositoryPort rocketRepository;

  public FlightService(FlightRepositoryPort flightRepository, RocketRepositoryPort rocketRepository) {
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

  public Flight createFlight(Flight flight) throws ValidationException {
    // Set defaults
    flight.setStatus(FlightStatus.SCHEDULED);

    // Validate
    String error = validateFlight(flight);

    if (error != null) {
      throw new IllegalArgumentException(error);
    }

    // Save
    return flightRepository.save(flight);
  }

  private String validateFlight(Flight flight) {

    // Business validations
    if (rocketRepository.findAll().stream().noneMatch(r -> r.getId().equals(flight.getRocket()))) {
      return "Rocket with id " + flight.getRocket() + " does not exist";
    }

    if (rocketRepository.findAll().stream().filter(r -> r.getId().equals(flight.getRocket())).findFirst().get()
        .getCapacity() < flight.getMinPassengers()) {
      return "Rocket capacity is less than minimum passengers required for the flight";
    }

    return null;
  }
}