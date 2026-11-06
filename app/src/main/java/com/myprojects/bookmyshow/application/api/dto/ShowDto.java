package com.myprojects.bookmyshow.application.api.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ShowDto(
    String movieId,
    String theaterId,
    List<Seat> seats,
    Instant startTime,
    Instant endTime
) {
}
