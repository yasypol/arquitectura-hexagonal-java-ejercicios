package com.astrobookings.sales.domain.ports.input;

import java.util.List;

import com.astrobookings.sales.domain.models.entities.Flight;
import com.astrobookings.shared.domain.models.ValidationException;

public interface FlightUseCases {
  List<Flight> getFlights(String statusFilter) ;
  Flight createFlight(Flight flight) throws ValidationException ;
}
