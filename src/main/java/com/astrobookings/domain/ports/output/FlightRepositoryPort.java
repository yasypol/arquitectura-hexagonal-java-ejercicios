package com.astrobookings.domain.ports.output;

import java.util.List;

import com.astrobookings.domain.models.Flight;

public interface FlightRepositoryPort {

  List<Flight> findAll();
  List<Flight> findByStatus(String status);
  Flight save(Flight flight);
}