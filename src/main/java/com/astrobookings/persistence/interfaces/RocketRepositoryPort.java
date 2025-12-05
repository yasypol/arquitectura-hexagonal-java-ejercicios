package com.astrobookings.persistence.interfaces;

import java.util.List;
import com.astrobookings.persistence.models.Rocket;

public interface RocketRepositoryPort {

  List<Rocket> findAll();
  Rocket save(Rocket rocket);
}