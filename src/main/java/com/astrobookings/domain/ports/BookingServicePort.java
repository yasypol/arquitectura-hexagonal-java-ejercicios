package com.astrobookings.domain.ports;

public interface BookingServicePort {

  String createBooking(String flightId, String passengerName) throws Exception ;
  public String getBookings(String flightId, String passengerName) throws Exception ;
}