package com.astrobookings.shared.domain.models;

public class ValidationException extends Exception {
  public ValidationException(String message) {
    super(message);
  }

}
