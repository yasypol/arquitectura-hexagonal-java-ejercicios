package com.astrobookings.domain.ports;

import java.util.List;

import com.astrobookings.domain.models.Rocket;

public interface RocketRepositoryPort {

  List<Rocket> findAll();
  Rocket save(Rocket rocket);
}