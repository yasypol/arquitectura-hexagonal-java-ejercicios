package com.astrobookings.domain.ports.input;

public interface BookingUseCases {

  String createBooking(String flightId, String passengerName) throws Exception ;
  public String getBookings(String flightId, String passengerName) throws Exception ;
}