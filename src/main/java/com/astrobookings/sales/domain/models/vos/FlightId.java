package com.astrobookings.sales.domain.models.vos;

import com.astrobookings.shared.domain.models.ValidationException;

public class FlightId {
  private String id;

  public FlightId(String id) throws ValidationException {

    if (id == null || id.trim().isEmpty()) {
      throw new IllegalArgumentException("Flight ID must be provided");
    }

    this.id = id;
  }

  public String getId() {
    return id;
  }

}