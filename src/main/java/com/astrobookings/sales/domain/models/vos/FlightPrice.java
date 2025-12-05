package com.astrobookings.sales.domain.models.vos;

import com.astrobookings.shared.domain.models.ValidationException;

public class FlightPrice {
  private double basePrice;

  public FlightPrice(double basePrice) throws ValidationException {

    if (basePrice <= 0) {
      throw new ValidationException("Base price must be positive");
    }

    this.basePrice = basePrice;
  }

  public double getBasePrice() {
    return basePrice;
  }

}