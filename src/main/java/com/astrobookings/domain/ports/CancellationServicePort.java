package com.astrobookings.domain.ports;


public interface CancellationServicePort {

  String cancelFlights() throws Exception;
}