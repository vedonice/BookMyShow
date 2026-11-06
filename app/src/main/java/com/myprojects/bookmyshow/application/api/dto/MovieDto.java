package com.myprojects.bookmyshow.application.api.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record MovieDto(
    String title,
    String description,
    Long duration,
    String language,
    MovieStatus movieStatus,
    Instant releaseDate
) {
}
