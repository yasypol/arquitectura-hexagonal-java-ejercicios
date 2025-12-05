package com.astrobookings.fleet.domain.models.entities;

import com.astrobookings.fleet.domain.models.vos.RocketCapacity;
import com.astrobookings.fleet.domain.models.vos.RocketId;
import com.astrobookings.fleet.domain.models.vos.RocketName;
import com.astrobookings.fleet.domain.models.vos.RocketSpeed;
import com.astrobookings.shared.domain.models.ValidationException;

public class Rocket {
  private RocketId id;
  private RocketName name;
  private RocketCapacity capacity;
  private RocketSpeed speed;

  public Rocket() {
  }

  public Rocket(String id, String name, int capacity, double speed) throws ValidationException {
    this.id = new RocketId(id);
    this.name = new RocketName(name);
    this.capacity = new RocketCapacity(capacity);
    this.speed = new RocketSpeed(speed);
  }

  public String getId() {
    return id.getId();
  }

  public void setId(String id) throws ValidationException {
    this.id = new RocketId(id);
  }

  public String getName() {
    return name.getName();
  }

  public int getCapacity() {
    return capacity.getCapacity();
  }

  public Double getSpeed() {
    return speed.getSpeed();
  }

}