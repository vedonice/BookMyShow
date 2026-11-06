package com.myprojects.bookmyshow.application.api.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record TheaterConfigResponse(
    String theaterId,
    String name,
    String location,
    List<Screen> screens,
    Instant createdAt,
    Instant updatedAt
) {}