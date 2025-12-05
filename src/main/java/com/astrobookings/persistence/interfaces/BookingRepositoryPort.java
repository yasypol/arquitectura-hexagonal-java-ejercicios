package com.astrobookings.persistence.interfaces;

import java.util.List;
import com.astrobookings.persistence.models.Booking;

public interface BookingRepositoryPort {

  List<Booking> findAll();
  List<Booking> findByFlightId(String flightId);
  List<Booking> findByPassengerName(String passengerName);
  Booking save(Booking booking);
}