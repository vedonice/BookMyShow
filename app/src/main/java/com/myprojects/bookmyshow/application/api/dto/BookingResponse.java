package com.myprojects.bookmyshow.application.api.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record BookingResponse (
   String bookingId,
   String showId,
   Instant showTime,
   String seatNumber,
   SeatType seatType,
   BookingStatus bookingStatus,
   Instant createdAt,
   Instant updatedAt
){}
