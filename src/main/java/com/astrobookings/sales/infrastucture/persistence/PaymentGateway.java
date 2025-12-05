package com.astrobookings.sales.infrastucture.persistence;

import java.util.UUID;

import com.astrobookings.sales.domain.ports.output.PaymentGatewayPort;

public class PaymentGateway implements PaymentGatewayPort{

  public String processPayment(double amount) throws Exception {
    System.out.println("[PAYMENT GATEWAY] Processing payment... Amount: " + amount);
    if (amount > 10000) {
      throw new Exception("Payment FAILED - Amount exceeds limit");
    }
    String transactionId = "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    System.out.println("[PAYMENT GATEWAY] Transaction ID: " + transactionId);
    return transactionId;
  }

  public void processRefund(String transactionId) {
    System.out.println("[PAYMENT GATEWAY] Processing refund for transaction: " + transactionId);
  }

  public void processRefund(String transactionId, double amount) {
    System.out.println("[PAYMENT GATEWAY] Processing refund for transaction " + transactionId + ": $" + amount);
    System.out.println("[PAYMENT GATEWAY] Refund successful for transaction " + transactionId);
  }
}