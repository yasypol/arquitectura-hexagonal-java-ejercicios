package com.astrobookings.domain;

import java.io.IOException;
import java.util.List;

import com.astrobookings.domain.exceptions.ValidationException;
import com.astrobookings.domain.models.CreateRocketCommand;
import com.astrobookings.domain.models.Rocket;
import com.astrobookings.domain.ports.input.RocketsUseCases;
import com.astrobookings.domain.ports.output.RocketRepositoryPort;

public class RocketService implements RocketsUseCases {

  private final RocketRepositoryPort rocketRepository;

  public RocketService(RocketRepositoryPort rocketRepository) {
    this.rocketRepository = rocketRepository;
  }

  public List<Rocket> getAllRockets() throws IOException {
    return rocketRepository.findAll();
  }

  public Rocket saveRocket(CreateRocketCommand command) throws IOException, ValidationException{
    
    validate(command);
    
    Rocket newRocket = new Rocket();
    newRocket.setName(command.name());
    newRocket.setCapacity(command.capacity());
    newRocket.setSpeed(command.maxSpeed());

    return rocketRepository.save(newRocket);
  }

  private void validate(CreateRocketCommand command) throws ValidationException {

    if (command.name() == null || command.name().isEmpty()) {
      throw new ValidationException("Rocket name is required");
    }
    if (command.capacity() <= 0) {
      throw new ValidationException("Rocket capacity must be greater than zero");
    }
  }
}