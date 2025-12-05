package com.astrobookings.sales.infrastucture.presentation;

import com.astrobookings.fleet.domain.RocketService;
import com.astrobookings.fleet.domain.ports.input.RocketsUseCases;
import com.astrobookings.fleet.domain.ports.output.RocketRepositoryPort;
import com.astrobookings.sales.domain.BookingService;
import com.astrobookings.sales.domain.CancellationService;
import com.astrobookings.sales.domain.FlightService;
import com.astrobookings.sales.domain.ports.input.BookingUseCases;
import com.astrobookings.sales.domain.ports.input.CancellationUseCases;
import com.astrobookings.sales.domain.ports.input.FlightUseCases;
import com.astrobookings.sales.domain.ports.output.BookingRepositoryPort;
import com.astrobookings.sales.domain.ports.output.FlightRepositoryPort;
import com.astrobookings.sales.domain.ports.output.NotificationServicePort;
import com.astrobookings.sales.domain.ports.output.PaymentGatewayPort;

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
