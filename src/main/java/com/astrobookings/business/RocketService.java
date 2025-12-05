package com.astrobookings.business;

import java.util.List;

import com.astrobookings.business.exceptions.ValidationException;
import com.astrobookings.business.interfaces.RocketServicePort;
import com.astrobookings.business.models.RocketDto;
import com.astrobookings.persistence.interfaces.RocketRepositoryPort;
import com.astrobookings.persistence.models.Rocket;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RocketService implements RocketServicePort {

  private final RocketRepositoryPort rocketRepository;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public RocketService(RocketRepositoryPort rocketRepository) {
    this.rocketRepository = rocketRepository;
  }

  public List<Rocket> getAllRockets() {
    return rocketRepository.findAll();
  }

  public String createRocket(RocketDto rocket) throws Exception {
    
    // Validate input
    if (rocket == null || rocket.name.trim().isEmpty()) {
      throw new IllegalArgumentException("Flight ID must be provided");
    }
    
    if (rocket.capacity <= 0 || rocket.capacity > 10) {
      throw new ValidationException("Rocket capacity must be between 1 and 10");
    }
    
    // Speed is optional, no validation

    // Create rocket
    Rocket newRocket = new Rocket();
    newRocket.setName(rocket.name);
    newRocket.setCapacity(rocket.capacity);
    newRocket.setSpeed(rocket.speed);

    rocketRepository.save(newRocket);

    // Return JSON (mixing responsibility)
    return objectMapper.writeValueAsString(newRocket);
  }

}