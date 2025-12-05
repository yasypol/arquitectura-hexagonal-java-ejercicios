package com.astrobookings.infrastructure.presentation;

import com.astrobookings.domain.BookingService;
import com.astrobookings.domain.CancellationService;
import com.astrobookings.domain.FlightService;
import com.astrobookings.domain.RocketService;
import com.astrobookings.domain.ports.input.BookingUseCases;
import com.astrobookings.domain.ports.input.CancellationUseCases;
import com.astrobookings.domain.ports.input.FlightUseCases;
import com.astrobookings.domain.ports.input.RocketsUseCases;
import com.astrobookings.domain.ports.output.BookingRepositoryPort;
import com.astrobookings.domain.ports.output.FlightRepositoryPort;
import com.astrobookings.domain.ports.output.NotificationServicePort;
import com.astrobookings.domain.ports.output.PaymentGatewayPort;
import com.astrobookings.domain.ports.output.RocketRepositoryPort;

public class UseCasesAdapterFactory {

    public static RocketsUseCases getRocketsUseCase(RocketRepositoryPort rocketsRepositoryPort) {
        return new RocketService(rocketsRepositoryPort);
    }

    public static FlightUseCases getFlightUseCase(FlightRepositoryPort flightRepository, RocketRepositoryPort rocketRepository) {
        return new FlightService(flightRepository, rocketRepository);
    }

    public static BookingUseCases getBookingUseCase(BookingRepositoryPort bookingRepository, FlightRepositoryPort flightRepository, RocketRepositoryPort rocketRepository, PaymentGatewayPort paymentGateway, NotificationServicePort notificationService) {
        return new BookingService(bookingRepository, flightRepository, rocketRepository, paymentGateway, notificationService);
    }

    public static CancellationUseCases getCancellationUseCase(FlightRepositoryPort flightRepository, BookingRepositoryPort bookingRepository, PaymentGatewayPort paymentGateway, NotificationServicePort notificationService) {
        return new CancellationService(flightRepository, bookingRepository, paymentGateway, notificationService);
    }
}
