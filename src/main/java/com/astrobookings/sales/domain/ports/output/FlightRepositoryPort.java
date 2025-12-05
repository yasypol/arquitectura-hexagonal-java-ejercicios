package com.astrobookings.sales.domain.ports.output;

import java.util.List;

import com.astrobookings.sales.domain.models.Flight;

public interface FlightRepositoryPort {

  List<Flight> findAll();
  List<Flight> findByStatus(String status);
  Flight save(Flight flight);
}