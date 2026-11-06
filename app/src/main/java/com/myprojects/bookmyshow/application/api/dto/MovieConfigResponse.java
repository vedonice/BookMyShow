package com.myprojects.bookmyshow.application.api.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record MovieConfigResponse (
   String title,
   String movieId,
   String description,
   MovieStatus movieStatus,
   String language,
   long duration,
   Instant releaseDate,
   Instant createdAt,
   Instant updatedAt
) {}
