package com.astrobookings.domain.ports;

import java.util.List;

import com.astrobookings.domain.dtos.RocketDto;
import com.astrobookings.domain.models.Rocket;

public interface RocketServicePort {

  String createRocket(RocketDto rocket) throws Exception;
  List<Rocket> getAllRockets();
}