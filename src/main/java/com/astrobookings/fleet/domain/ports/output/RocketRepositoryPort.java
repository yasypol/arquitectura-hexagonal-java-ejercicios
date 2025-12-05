package com.astrobookings.fleet.domain.ports.output;

import java.util.List;

import com.astrobookings.fleet.domain.models.Rocket;

public interface RocketRepositoryPort {

  List<Rocket> findAll();
  Rocket save(Rocket rocket);
}