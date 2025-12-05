package com.astrobookings.persistence.factory;

import com.astrobookings.persistence.implementations.InMemoryBookingRepository;
import com.astrobookings.persistence.implementations.InMemoryFlightRepository;
import com.astrobookings.persistence.implementations.InMemoryRocketRepository;
import com.astrobookings.persistence.interfaces.BookingRepositoryPort;
import com.astrobookings.persistence.interfaces.FlightRepositoryPort;
import com.astrobookings.persistence.interfaces.RocketRepositoryPort;

public class RepositoryFactory {

    public static RocketRepositoryPort createRocketRepository() {
        // Here you can switch between different implementations
        return new InMemoryRocketRepository();
    }

    public static FlightRepositoryPort createFlightRepository() {
        // Placeholder for FlightRepositoryPort implementation
        return new InMemoryFlightRepository();
    }

    public static BookingRepositoryPort createBookingRepository() {
        // Placeholder for BookingRepositoryPort implementation
        return new InMemoryBookingRepository();
    }
}
