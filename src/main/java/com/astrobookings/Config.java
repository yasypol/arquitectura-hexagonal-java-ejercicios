package com.astrobookings;

import com.astrobookings.domain.ports.input.BookingUseCases;
import com.astrobookings.domain.ports.input.CancellationUseCases;
import com.astrobookings.domain.ports.input.FlightUseCases;
import com.astrobookings.domain.ports.input.RocketsUseCases;
import com.astrobookings.domain.ports.output.BookingRepositoryPort;
import com.astrobookings.domain.ports.output.FlightRepositoryPort;
import com.astrobookings.domain.ports.output.NotificationServicePort;
import com.astrobookings.domain.ports.output.PaymentGatewayPort;
import com.astrobookings.domain.ports.output.RocketRepositoryPort;
import com.astrobookings.infrastructure.persistence.PersistenceAdapterFactory;
import com.astrobookings.infrastructure.presentation.UseCasesAdapterFactory;

public class Config {

  static final RocketRepositoryPort rocketRepository = PersistenceAdapterFactory.getRocketRepositoryAdapter();
  static final FlightRepositoryPort flightRepository = PersistenceAdapterFactory.getFlightRepositoryAdapter();
  static final BookingRepositoryPort bookingRepository = PersistenceAdapterFactory.getBookingRepositoryAdapter();
  static final PaymentGatewayPort paymentGateway = PersistenceAdapterFactory.getPaymentGatewayAdapter();
  static final NotificationServicePort notificationService = PersistenceAdapterFactory.getNotificationServiceAdapter();

  static final RocketsUseCases rocketUseCase = UseCasesAdapterFactory.getRocketsUseCase(rocketRepository);
  
  static final FlightUseCases flightUseCase = UseCasesAdapterFactory.getFlightUseCase(flightRepository, rocketRepository);
  
  static final BookingUseCases bookingUseCase = UseCasesAdapterFactory.getBookingUseCase(
    bookingRepository, flightRepository, rocketRepository,
    paymentGateway,
    notificationService);

  static final CancellationUseCases cancellationUseCases = UseCasesAdapterFactory.getCancellationUseCase(
    flightRepository, bookingRepository, paymentGateway, notificationService);

}
