package com.astrobookings.persistence.interfaces;

import java.util.List;
import com.astrobookings.persistence.models.Flight;

public interface FlightRepositoryPort {

  List<Flight> findAll();
  List<Flight> findByStatus(String status);
  Flight save(Flight flight);
}