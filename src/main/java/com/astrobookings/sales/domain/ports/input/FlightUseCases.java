package com.astrobookings.sales.domain.ports.input;

import java.util.List;

import com.astrobookings.sales.domain.models.Flight;

public interface FlightUseCases {
    List<Flight> getFlights(String statusFilter) ;
  Flight createFlight(Flight flight);
}
