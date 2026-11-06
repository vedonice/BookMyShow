package com.myprojects.bookmyshow.domain.entity;

import com.myprojects.bookmyshow.domain.dto.PaymentStatus;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Builder ( toBuilder = true)
@Document(collection = "bms-payments")
public record Payment(
    String id,
    String userId,
    String bankName,
    String accountNumber,
    long amount,
    String bookingId,
    String transactionId,
    PaymentStatus paymentStatus,
    Instant createdAt,
    Instant updatedAt
) {}