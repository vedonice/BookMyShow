package com.myprojects.bookmyshow.application.api.dto;

import com.myprojects.bookmyshow.domain.dto.PaymentDto;
import lombok.Builder;

@Builder
public record BookingRequest (
   String showId,
   String seatId,
   String userId
) {}
