package com.astrobookings.sales.infrastucture.adapters;

import com.astrobookings.fleet.domain.models.entities.Rocket;
import com.astrobookings.fleet.domain.ports.output.RocketRepositoryPort;
import com.astrobookings.sales.domain.models.entities.Flight;
import com.astrobookings.sales.domain.ports.output.FlightInfoProvider;
import com.astrobookings.sales.domain.ports.output.FlightRepositoryPort;

public class FleetAdapter implements FlightInfoProvider {

    private final FlightRepositoryPort flightRepository;
    private final RocketRepositoryPort rocketRepository;

    public FleetAdapter(FlightRepositoryPort flightRepository, RocketRepositoryPort rocketRepository) {
        this.flightRepository = flightRepository;
        this.rocketRepository = rocketRepository;
    }

    @Override
    public FlightInfo getFlightById(String id) {
        
        Flight flight = flightRepository.findById(id);
        if (flight == null) {
            return null;
        }

        Rocket rocket = rocketRepository.findById(flight.getRocket());
        int capacity = rocket != null ? rocket.getCapacity() : 0;
        
        return new FlightInfo(
            flight.getId(),
            flight.getRocket(),
            flight.getDepartureDate(),
            flight.getBasePrice(),
            flight.getStatus().name(),
            flight.getMinPassengers(),
            capacity
        );
    }
}
