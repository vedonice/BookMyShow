package com.myprojects.bookmyshow.application.api.dto;

import lombok.Builder;

@Builder
public record SeatConfigRequest(
    String seatId,
    String seatNumber,
    SeatType seatType,
    long price,
    SeatStatus seatStatus
) {}
