package com.astrobookings.infrastructure.persistence;

import com.astrobookings.domain.NotificationService;
import com.astrobookings.domain.ports.output.BookingRepositoryPort;
import com.astrobookings.domain.ports.output.FlightRepositoryPort;
import com.astrobookings.domain.ports.output.NotificationServicePort;
import com.astrobookings.domain.ports.output.PaymentGatewayPort;
import com.astrobookings.domain.ports.output.RocketRepositoryPort;

public class PersistenceAdapterFactory {

    public static RocketRepositoryPort getRocketRepositoryAdapter() {
        
        return new InMemoryRocketRepositoryAdapter();
    }

    public static FlightRepositoryPort getFlightRepositoryAdapter() {
        
        return new InMemoryFlightRepositoryAdapter();
    }

    public static BookingRepositoryPort getBookingRepositoryAdapter() {
        
        return new InMemoryBookingRepositoryAdapter();
    }

    public static PaymentGatewayPort getPaymentGatewayAdapter() {
        
        return new PaymentGateway();
    }

    public static NotificationServicePort getNotificationServiceAdapter() {
        
        return new NotificationService();
    }
}
