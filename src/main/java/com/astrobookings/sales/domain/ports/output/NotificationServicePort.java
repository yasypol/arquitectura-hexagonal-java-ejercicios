package com.astrobookings.sales.domain.ports.output;

import java.util.List;

import com.astrobookings.sales.domain.models.entities.Booking;

public interface NotificationServicePort {

  void notifyConfirmation(String flightId, int passengerCount);

  void notifyCancellation(String flightId, int passengerCount);

  void notifyCancellation(String flightId, List<Booking> bookings);
}