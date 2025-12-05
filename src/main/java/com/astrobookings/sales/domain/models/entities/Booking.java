package com.astrobookings.sales.domain.models.entities;

import com.astrobookings.sales.domain.models.vos.BookingPassengerName;
import com.astrobookings.shared.domain.models.ValidationException;

public class Booking {
  private String id;
  private String flightId;
  private BookingPassengerName passengerName;
  private double finalPrice;
  private String paymentTransactionId;

  public Booking(String id, String flightId, String passengerName, double finalPrice, String paymentTransactionId) throws ValidationException {
    this.id = id;
    this.flightId = flightId;
    this.passengerName = new BookingPassengerName(passengerName);
    this.finalPrice = finalPrice;
    this.paymentTransactionId = paymentTransactionId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFlightId() {
    return flightId;
  }

  public void setFlightId(String flightId) {
    this.flightId = flightId;
  }

  public String getPassengerName() {
    return passengerName.getPassengerName();
  }

  public double getFinalPrice() {
    return finalPrice;
  }

  public void setFinalPrice(double finalPrice) {
    this.finalPrice = finalPrice;
  }

  public String getPaymentTransactionId() {
    return paymentTransactionId;
  }

  public void setPaymentTransactionId(String paymentTransactionId) {
    this.paymentTransactionId = paymentTransactionId;
  }
}