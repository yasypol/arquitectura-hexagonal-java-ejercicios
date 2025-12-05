package com.astrobookings.fleet.infrastucture.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.astrobookings.fleet.domain.models.entities.Rocket;
import com.astrobookings.fleet.domain.ports.output.RocketRepositoryPort;
import com.astrobookings.shared.domain.models.ValidationException;

public class InMemoryRocketRepositoryAdapter implements RocketRepositoryPort {
  
  private static final Map<String, Rocket> rockets = new HashMap<>();
  private static int nextId = 1;

  static {
    // Pre-load one rocket
    var rocketId = "00000000-0000-0000-0000-000000000001";
    Rocket falcon9;

    try {
      falcon9 = new Rocket(rocketId, "Falcon 9", 7, 27000.0);
      rockets.put(rocketId, falcon9);
      nextId = 2;
    } catch (ValidationException e) {
      e.printStackTrace();
    }
  }
  
  public Rocket findById(String id) {
    
    return rockets.values().stream()
        .filter(rocket -> rocket.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  public List<Rocket> findAll() {
    return new ArrayList<>(rockets.values());
  }

  public Rocket save(Rocket rocket) throws ValidationException {

    if (rocket.getId() == null) {
      rocket.setId("r" + nextId++);
    }

    rockets.put(rocket.getId(), rocket);
    
    return rocket;
  }
}