package com.astrobookings.sales.domain.models.vos;

import java.time.LocalDateTime;

import com.astrobookings.shared.domain.models.ValidationException;

public class FlightDepartureDate {
  private LocalDateTime departureDate;

  public FlightDepartureDate(LocalDateTime departureDate) throws ValidationException {
    
    if (departureDate == null) {
      throw new ValidationException("Departure date must be provided");
    }

    LocalDateTime now = LocalDateTime.now();

    if (!departureDate.isAfter(now)) {
      throw new ValidationException("Departure date must be in the future");
    }
    
    LocalDateTime oneYearAhead = now.plusYears(1);

    if (departureDate.isAfter(oneYearAhead)) {
      throw new ValidationException("Departure date cannot be more than 1 year ahead");
    }
    
    this.departureDate = departureDate;
  }

  public LocalDateTime getDepartureDate() {
    return departureDate;
  }
}