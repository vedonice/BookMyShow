package com.myprojects.bookmyshow.application.api.dto;

public record Seat(
    String id,
    Long price,
    String seatNumber,
    SeatType seatType,
    SeatStatus seatStatus
) {}
