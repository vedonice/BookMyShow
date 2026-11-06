package com.myprojects.bookmyshow.application.api.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record SeatConfigResponse(
    String seatId,
    String seatNumber,
    SeatType seatType,
    long price,
    SeatStatus seatStatus,
    Instant createdAt,
    Instant updatedAt
) {}
