package com.myprojects.bookmyshow.domain.dto;

import lombok.Builder;

@Builder
public record PaymentStatusResponse(
    String transactionId,
    String bookingId,
    PaymentStatus paymentStatus
) {}