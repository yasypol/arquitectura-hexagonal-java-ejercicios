package com.astrobookings.fleet.domain;

import java.io.IOException;
import java.util.List;

import com.astrobookings.fleet.domain.models.CreateRocketCommand;
import com.astrobookings.fleet.domain.models.entities.Rocket;
import com.astrobookings.fleet.domain.ports.input.RocketsUseCases;
import com.astrobookings.fleet.domain.ports.output.RocketRepositoryPort;
import com.astrobookings.shared.domain.models.ValidationException;

public class RocketService implements RocketsUseCases {

  private final RocketRepositoryPort rocketRepository;

  public RocketService(RocketRepositoryPort rocketRepository) {
    this.rocketRepository = rocketRepository;
  }

  public List<Rocket> getAllRockets() throws IOException {
    return rocketRepository.findAll();
  }

  public Rocket saveRocket(CreateRocketCommand command) throws IOException, ValidationException{
    
    Rocket newRocket = new Rocket(command.id(), command.name(), command.capacity(), command.maxSpeed());

    return rocketRepository.save(newRocket);
  }
}