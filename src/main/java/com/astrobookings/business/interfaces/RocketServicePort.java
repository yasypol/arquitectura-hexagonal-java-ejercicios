package com.astrobookings.business.interfaces;

import java.util.List;

import com.astrobookings.business.models.RocketDto;
import com.astrobookings.persistence.models.Rocket;

public interface RocketServicePort {

  String createRocket(RocketDto rocket) throws Exception;
  List<Rocket> getAllRockets();
}