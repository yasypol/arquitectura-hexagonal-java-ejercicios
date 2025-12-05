package com.astrobookings.sales.infrastucture.persistence;

import com.astrobookings.fleet.domain.ports.output.RocketRepositoryPort;
import com.astrobookings.fleet.infrastucture.persistence.InMemoryRocketRepositoryAdapter;
import com.astrobookings.sales.domain.NotificationService;
import com.astrobookings.sales.domain.ports.output.BookingRepositoryPort;
import com.astrobookings.sales.domain.ports.output.FlightRepositoryPort;
import com.astrobookings.sales.domain.ports.output.NotificationServicePort;
import com.astrobookings.sales.domain.ports.output.PaymentGatewayPort;

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
