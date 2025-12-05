package com.astrobookings.fleet.domain.models.vos;

import com.astrobookings.shared.domain.models.ValidationException;

public class RocketSpeed {
  
  private Double speed;

  public RocketSpeed(Double speed) throws ValidationException {
    
    if (speed <= 0) {
      throw new ValidationException("Rocket speed must be greater than zero");
    }
    
    this.speed = speed;
  }

  public Double getSpeed() {
    return speed;
  }

}