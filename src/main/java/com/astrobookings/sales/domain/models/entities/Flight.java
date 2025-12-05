package com.astrobookings.sales.domain.models.entities;

import java.time.LocalDateTime;

import com.astrobookings.sales.domain.models.RocketInfo;
import com.astrobookings.sales.domain.models.vos.FlightDepartureDate;
import com.astrobookings.sales.domain.models.vos.FlightId;
import com.astrobookings.sales.domain.models.vos.FlightMinPassengers;
import com.astrobookings.sales.domain.models.vos.FlightPrice;
import com.astrobookings.sales.domain.models.vos.FlightStatus;
import com.astrobookings.shared.domain.models.ValidationException;

public class Flight {

  private FlightId id;
  private RocketInfo rocket;
  private FlightDepartureDate departureDate;
  private FlightPrice basePrice;
  private FlightStatus status;
  private FlightMinPassengers minPassengers;

  public Flight(String id, RocketInfo rocket, LocalDateTime departureDate, double basePrice, FlightStatus status,
      int minPassengers) throws ValidationException {
    
    this.id = new FlightId(id);
    this.rocket = rocket;
    this.departureDate = new FlightDepartureDate(departureDate);
    this.basePrice = new FlightPrice(basePrice);
    this.status = status;
    this.minPassengers = new FlightMinPassengers(minPassengers);
  }

  public String getId() {
    return id.getId();
  }

  public void setId(String id) throws ValidationException {
    this.id = new FlightId(id);
  }

  public String getRocket() {
    return rocket.getId();
  }

  public LocalDateTime getDepartureDate() {
    return departureDate.getDepartureDate();
  }

  public double getBasePrice() {
    return basePrice.getBasePrice();
  }

  public FlightStatus getStatus() {
    return status;
  }

  public void setStatus(FlightStatus status) {
    this.status = status;
  }

  public int getMinPassengers() {
    return minPassengers.getMinPassengers();
  }
}