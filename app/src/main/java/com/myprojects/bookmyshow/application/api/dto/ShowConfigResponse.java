package com.myprojects.bookmyshow.application.api.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ShowConfigResponse(
    String showId,
    String movieId,
    String theaterId,
    List<Screen> screens,
    Instant startTime,
    Instant endTime,
    Instant createdAt,
    Instant updatedAt
) {
}
