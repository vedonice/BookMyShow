package com.myprojects.bookmyshow.application.api.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ShowConfigRequest(
  String movieId,
  String theaterId,
  List<Screen> screens,
  Instant startTime,
  Instant endTime
) {}
