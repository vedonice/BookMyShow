package com.myprojects.bookmyshow.domain.dto;

import lombok.Builder;

@Builder
public record PaymentResponse(
    String status,
    String statusCode,
    String statusMessage,
    PaymentStatusResponse paymentStatusResponse
) {}