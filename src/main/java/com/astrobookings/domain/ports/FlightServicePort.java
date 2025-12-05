package com.astrobookings.domain.ports;

import java.util.List;

import com.astrobookings.domain.models.Flight;

public interface FlightServicePort {

  List<Flight> getFlights(String statusFilter) ;
  Flight createFlight(Flight flight);
}