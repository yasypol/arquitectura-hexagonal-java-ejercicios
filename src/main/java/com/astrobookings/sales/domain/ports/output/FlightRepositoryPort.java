package com.astrobookings.sales.domain.ports.output;

import java.util.List;

import com.astrobookings.sales.domain.models.entities.Flight;
import com.astrobookings.shared.domain.models.ValidationException;

public interface FlightRepositoryPort {
  
  Flight findById(String id);
  List<Flight> findAll();
  List<Flight> findByStatus(String status);
  Flight save(Flight flight) throws ValidationException ;
}