package com.astrobookings.sales.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.astrobookings.sales.domain.models.entities.Booking;
import com.astrobookings.sales.domain.models.entities.Flight;
import com.astrobookings.sales.domain.models.vos.FlightStatus;
import com.astrobookings.sales.domain.ports.input.CancellationUseCases;
import com.astrobookings.sales.domain.ports.output.BookingRepositoryPort;
import com.astrobookings.sales.domain.ports.output.FlightRepositoryPort;
import com.astrobookings.sales.domain.ports.output.NotificationServicePort;
import com.astrobookings.sales.domain.ports.output.PaymentGatewayPort;

public class CancellationService implements CancellationUseCases {

  private final FlightRepositoryPort flightRepository;
  private final BookingRepositoryPort bookingRepository;
  private final PaymentGatewayPort paymentGateway;
  private final NotificationServicePort notificationService;

  public CancellationService(FlightRepositoryPort flightRepository, BookingRepositoryPort bookingRepository, PaymentGatewayPort paymentGateway, NotificationServicePort notificationService) {
    this.flightRepository = flightRepository;
    this.bookingRepository = bookingRepository;
    this.paymentGateway = paymentGateway;
    this.notificationService = notificationService;
  }

  public String cancelFlights() throws Exception {

    List<Flight> flights = flightRepository.findAll();
    int cancelledCount = 0;
    LocalDateTime now = LocalDateTime.now();

    for (Flight flight : flights) {

      if (flight.getStatus() == FlightStatus.SCHEDULED) {

        long daysUntilDeparture = ChronoUnit.DAYS.between(now, flight.getDepartureDate());
        
        if (daysUntilDeparture <= 7) {

          List<Booking> bookings = bookingRepository.findByFlightId(flight.getId());
          
          if (bookings.size() < flight.getMinPassengers()) {
          
            // Cancel flight
            System.out.println("[CANCELLATION SERVICE] Cancelling flight " + flight.getId() + " - Only "
                + bookings.size() + "/5 passengers, departing in " + daysUntilDeparture + " days");
            flight.setStatus(FlightStatus.CANCELLED);
            flightRepository.save(flight);

            // Refund bookings
            for (Booking booking : bookings) {
              paymentGateway.processRefund(booking.getPaymentTransactionId(), booking.getFinalPrice());
            }

            // Notify
            notificationService.notifyCancellation(flight.getId(), bookings);
            cancelledCount++;
          }
        }
      }
    }

    return "{\"message\": \"Flight cancellation check completed\", \"cancelledFlights\": " + cancelledCount + "}";
  }
}