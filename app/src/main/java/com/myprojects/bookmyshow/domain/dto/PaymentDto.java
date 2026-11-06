package com.myprojects.bookmyshow.domain.dto;

import com.myprojects.bookmyshow.domain.entity.Payment;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record PaymentDto(
    String userId,
    String bankName,
    String accountNumber,
    long amount,
    String bookingId
) {

  public static Payment toPayment(PaymentDto paymentDto) {
    return Payment.builder()
        .id("PaymentID_" + UUID.randomUUID())
        .userId(paymentDto.userId)
        .bankName(paymentDto.bankName)
        .accountNumber(paymentDto.accountNumber())
        .amount(paymentDto.amount)
        .bookingId(paymentDto.bookingId)
        .transactionId("TransactionID_" + UUID.randomUUID())
        .paymentStatus(PaymentStatus.PENDING)
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  public static Payment toPayment(PaymentDto paymentDto, PaymentStatus paymentStatus) {
    return Payment.builder()
        .id("PaymentID_" + UUID.randomUUID())
        .userId(paymentDto.userId)
        .bankName(paymentDto.bankName)
        .accountNumber(paymentDto.accountNumber())
        .amount(paymentDto.amount)
        .bookingId(paymentDto.bookingId)
        .transactionId("TransactionID_" + UUID.randomUUID())
        .paymentStatus(paymentStatus)
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  public static PaymentDto fromPayment(Payment payment) {
    return PaymentDto.builder()
        .accountNumber(payment.accountNumber())
        .amount(payment.amount())
        .bookingId(payment.bookingId())
        .userId(payment.userId())
        .bankName(payment.bankName())
        .build();
  }
}