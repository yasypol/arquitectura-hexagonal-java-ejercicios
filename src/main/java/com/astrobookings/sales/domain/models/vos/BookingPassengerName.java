package com.astrobookings.sales.domain.models.vos;

import com.astrobookings.shared.domain.models.ValidationException;

public class BookingPassengerName {

  private String passengerName;

  public BookingPassengerName(String passengerName) throws ValidationException {
    
    if (passengerName == null || passengerName.trim().isEmpty()) {
      throw new ValidationException("Passenger name must be provided");
    }
    
    this.passengerName = passengerName;
  }

  public String getPassengerName() {
    return passengerName;
  }
}