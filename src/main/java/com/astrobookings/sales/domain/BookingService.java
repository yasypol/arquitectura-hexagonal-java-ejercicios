package com.astrobookings.sales.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.astrobookings.fleet.domain.models.entities.Rocket;
import com.astrobookings.fleet.domain.ports.output.RocketRepositoryPort;
import com.astrobookings.sales.domain.models.entities.Booking;
import com.astrobookings.sales.domain.models.entities.Flight;
import com.astrobookings.sales.domain.models.vos.FlightStatus;
import com.astrobookings.sales.domain.ports.input.BookingUseCases;
import com.astrobookings.sales.domain.ports.output.BookingRepositoryPort;
import com.astrobookings.sales.domain.ports.output.FlightRepositoryPort;
import com.astrobookings.sales.domain.ports.output.NotificationServicePort;
import com.astrobookings.sales.domain.ports.output.PaymentGatewayPort;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookingService implements BookingUseCases{
  
  private final BookingRepositoryPort bookingRepository;
  private final FlightRepositoryPort flightRepository;
  private final RocketRepositoryPort rocketRepository;
  private final ObjectMapper objectMapper = new ObjectMapper();
  private final PaymentGatewayPort paymentGateway;
  private final NotificationServicePort notificationService;

  public BookingService(BookingRepositoryPort bookingRepository, FlightRepositoryPort flightRepository, 
    RocketRepositoryPort rocketRepository, PaymentGatewayPort paymentGateway, NotificationServicePort notificationService) {

    this.bookingRepository = bookingRepository;
    this.flightRepository = flightRepository;
    this.rocketRepository = rocketRepository;
    this.paymentGateway = paymentGateway;
    this.notificationService = notificationService;
  }

  public String createBooking(String flightId, String passengerName) throws Exception {
    
    // Find flight
    Flight flight = flightRepository.findAll().stream()
        .filter(f -> f.getId().equals(flightId))
        .findFirst()
        .orElse(null);

    if (flight == null) {
      throw new IllegalArgumentException("Flight not found");
    }

    // Check flight status
    if (flight.getStatus() == FlightStatus.CANCELLED || flight.getStatus() == FlightStatus.SOLD_OUT) {
      throw new IllegalArgumentException("Flight is not available for booking");
    }

    // Get rocket capacity
    Rocket rocket = rocketRepository.findAll().stream()
        .filter(r -> r.getId().equals(flight.getRocket()))
        .findFirst()
        .orElse(null);
    if (rocket == null) {
      throw new IllegalArgumentException("Rocket not found");
    }
    
    int capacity = rocket.getCapacity();

    // Count current bookings
    List<Booking> existingBookings = bookingRepository.findByFlightId(flightId);
    int currentBookings = existingBookings.size();

    if (currentBookings >= capacity) {
      throw new IllegalArgumentException("Flight is sold out");
    }

    // Calculate discount
    double discount = calculateDiscount(flight, currentBookings, capacity);

    // Final price
    double finalPrice = flight.getBasePrice() * (1 - discount);

    // Process payment
    String transactionId = paymentGateway.processPayment(finalPrice);

    // Create booking
    Booking booking = new Booking(null, flightId, passengerName, finalPrice, transactionId);
    Booking savedBooking = bookingRepository.save(booking);

    // Update flight status
    currentBookings++;

    if (currentBookings >= capacity) {
      flight.setStatus(FlightStatus.SOLD_OUT);
    } 
    else if (currentBookings >= flight.getMinPassengers() && flight.getStatus() == FlightStatus.SCHEDULED) {
      flight.setStatus(FlightStatus.CONFIRMED);
      notificationService.notifyConfirmation(flightId, currentBookings);
    }

    flightRepository.save(flight);

    // Return JSON (mixing responsibility)
    return objectMapper.writeValueAsString(savedBooking);
  }

  private double calculateDiscount(Flight flight, int currentBookings, int capacity) {
    LocalDateTime now = LocalDateTime.now();
    long daysUntilDeparture = ChronoUnit.DAYS.between(now, flight.getDepartureDate());

    // Precedence: only one discount
    if (currentBookings + 1 == capacity) {
      return 0.0; // Last seat, no discount
    } 
    else if (currentBookings + 1 == flight.getMinPassengers()) {
      return 0.3; // One short of min, 30% off
    } 
    else if (daysUntilDeparture > 180) {
      return 0.1; // >6 months, 10% off
    } 
    else if (daysUntilDeparture >= 7 && daysUntilDeparture <= 30) {
      return 0.2; // 1 month to 1 week, 20% off
    } 
    else {
      return 0.0; // No discount
    }
  }

  public String getBookings(String flightId, String passengerName) throws Exception {
    List<Booking> bookings;
    if (flightId != null && !flightId.isEmpty()) {
      bookings = bookingRepository.findByFlightId(flightId);
      if (passengerName != null && !passengerName.isEmpty()) {
        bookings = bookings.stream()
            .filter(b -> b.getPassengerName().equalsIgnoreCase(passengerName))
            .collect(java.util.stream.Collectors.toList());
      }
    } else if (passengerName != null && !passengerName.isEmpty()) {
      bookings = bookingRepository.findByPassengerName(passengerName);
    } else {
      bookings = bookingRepository.findAll();
    }
    return objectMapper.writeValueAsString(bookings);
  }
}