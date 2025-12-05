package com.astrobookings.fleet.domain.models.vos;

import com.astrobookings.shared.domain.models.ValidationException;

public class RocketName {
  
  private String name;

  public RocketName(String name) throws ValidationException {
    
    if (name == null || name.trim().isEmpty()) {
      throw new ValidationException("Rocket name must be provided");
    }
    
    this.name = name;
  }

  public String getName() {
    return name;
  }

}