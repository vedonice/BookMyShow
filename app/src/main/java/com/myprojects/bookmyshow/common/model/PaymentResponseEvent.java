package com.myprojects.bookmyshow.common.model;

import com.myprojects.bookmyshow.domain.dto.PaymentStatus;
import lombok.Builder;

import java.time.Instant;

@Builder
public record PaymentResponseEvent(
    String transactionId,
    String bookingId,
    PaymentStatus paymentStatus,
    Instant createdAt
) {}