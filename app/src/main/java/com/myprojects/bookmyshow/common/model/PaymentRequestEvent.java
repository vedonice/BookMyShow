package com.myprojects.bookmyshow.common.model;

import com.myprojects.bookmyshow.domain.dto.PaymentStatus;
import lombok.Builder;

import java.time.Instant;

@Builder
public record PaymentRequestEvent(
    String userId,
    String bankName,
    String accountNumber,
    long amount,
    String bookingId,
    String transactionId,
    PaymentStatus paymentStatus,
    Instant createdAt
) {}
