package com.astrobookings.fleet.domain.models.vos;

import com.astrobookings.shared.domain.models.ValidationException;

public class RocketCapacity {
  
  private int capacity;

  public RocketCapacity(int capacity) throws ValidationException {
    
    if (capacity <= 0 || capacity > 10) {
      throw new ValidationException("Rocket capacity must be greater than zero");
    }
    
    this.capacity = capacity;
  }

  public int getCapacity() {
    return capacity;
  }

}