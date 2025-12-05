package com.astrobookings.presentation.factory;

import com.astrobookings.domain.ports.BookingRepositoryPort;
import com.astrobookings.domain.ports.FlightRepositoryPort;
import com.astrobookings.domain.ports.RocketRepositoryPort;
import com.astrobookings.infrastructure.adapters.InMemoryBookingAdapter;
import com.astrobookings.infrastructure.adapters.InMemoryFlightAdapter;
import com.astrobookings.infrastructure.adapters.InMemoryRocketAdapter;

public class RepositoryAdapterFactory {

    public static RocketRepositoryPort getRocketRepositoryAdapter() {
        
        return new InMemoryRocketAdapter();
    }

    public static FlightRepositoryPort getFlightRepositoryAdapter() {
        
        return new InMemoryFlightAdapter();
    }

    public static BookingRepositoryPort getBookingRepositoryAdapter() {
        
        return new InMemoryBookingAdapter();
    }
}
