package com.astrobookings.sales.domain.models.vos;

import com.astrobookings.shared.domain.models.ValidationException;

public class FlightMinPassengers {
  private int minPassengers;

  public FlightMinPassengers(int minPassengers) throws ValidationException {

    if (minPassengers < 0) {
      throw new IllegalArgumentException("Minimum passengers must be greater than zero");
    }

    if (minPassengers == 0) {
      this.minPassengers = 5;
    }
    else {
      this.minPassengers = minPassengers;
    }
  }

  public int getMinPassengers() {
    return minPassengers;
  }
}