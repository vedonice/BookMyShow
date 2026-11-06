package com.myprojects.bookmyshow.application.api.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record MovieConfigRequest (
   String title,
   String description,
   Instant releaseDate,
   Long duration,
   String language,
   MovieStatus movieStatus
) {}
