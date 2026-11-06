package com.myprojects.bookmyshow.application.api.service;

import com.myprojects.bookmyshow.application.api.dto.TheaterConfigRequest;
import com.myprojects.bookmyshow.application.api.dto.TheaterConfigResponse;
import com.myprojects.bookmyshow.application.api.entities.Theater;
import com.myprojects.bookmyshow.application.api.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TheaterService {
  private final TheaterRepository theaterRepository;

  public TheaterService(TheaterRepository theaterRepository) {
    this.theaterRepository = theaterRepository;
  }

  public TheaterConfigResponse createTheater(TheaterConfigRequest theaterConfigRequest) {
    var theater = getTheater(theaterConfigRequest);
    Theater saved = theaterRepository.save(theater);
    return getTheaterConfigResponse(saved);
  }

  public TheaterConfigResponse getTheaterById(String theaterId) {
    return theaterRepository.findById(theaterId)
        .map(this::getTheaterConfigResponse)
        .orElseThrow(() -> new RuntimeException("Theater not found: " + theaterId));
  }

  public List<TheaterConfigResponse> getAllTheaters() {
    return theaterRepository.findAll().stream()
        .map(theater -> TheaterConfigResponse.builder()
            .theaterId(theater.getId())
            .name(theater.getName())
            .location(theater.getLocation())
            .screens(theater.getScreens())
            .createdAt(theater.getCreatedAt())
            .updatedAt(theater.getUpdatedAt())
            .build())
        .toList();
  }

  private TheaterConfigResponse getTheaterConfigResponse(Theater saved) {
    return TheaterConfigResponse.builder()
        .theaterId(saved.getId())
        .name(saved.getName())
        .location(saved.getLocation())
        .screens(saved.getScreens())
        .createdAt(saved.getCreatedAt())
        .updatedAt(saved.getUpdatedAt())
        .build();
  }

  private Theater getTheater(TheaterConfigRequest theaterConfigRequest) {
    return Theater.builder()
        .id("TheaterID_" + UUID.randomUUID())
        .name(theaterConfigRequest.name())
        .location(theaterConfigRequest.location())
        .screens(theaterConfigRequest.screens())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }
}
