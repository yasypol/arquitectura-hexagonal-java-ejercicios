package com.astrobookings.fleet.domain.ports.output;

import java.util.List;

import com.astrobookings.fleet.domain.models.entities.Rocket;
import com.astrobookings.shared.domain.models.ValidationException;

public interface RocketRepositoryPort {
  
  Rocket findById(String id);
  List<Rocket> findAll();
  Rocket save(Rocket rocket) throws ValidationException;
}