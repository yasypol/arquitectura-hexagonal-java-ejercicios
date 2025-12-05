package com.astrobookings.domain.ports.input;

import java.util.List;

import com.astrobookings.domain.models.Flight;

public interface FlightUseCases {
    List<Flight> getFlights(String statusFilter) ;
  Flight createFlight(Flight flight);
}
