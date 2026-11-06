package com.myprojects.bookmyshow.application.api.service;

import com.myprojects.bookmyshow.application.api.dto.SeatConfigRequest;
import com.myprojects.bookmyshow.application.api.dto.SeatConfigResponse;
import com.myprojects.bookmyshow.application.api.entities.Seat;
import com.myprojects.bookmyshow.application.api.dto.SeatStatus;
import com.myprojects.bookmyshow.application.api.repository.SeatRepository;
import com.myprojects.bookmyshow.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class SeatService {
  private final SeatRepository seatRepository;

  public SeatService(SeatRepository seatRepository) {
    this.seatRepository = seatRepository;
  }

  public SeatConfigResponse createSeat(SeatConfigRequest seatConfigRequest) {
    var seat = getSeat(seatConfigRequest);
    var savedSeat = seatRepository.save(seat);
    return getSeatConfigResponse(savedSeat);
  }

  public SeatConfigResponse getSeatById(String seatId) {
    return seatRepository.findById(seatId)
        .map(this::getSeatConfigResponse)
        .orElseThrow(() -> new ResourceNotFoundException("Seat not found: " + seatId));
  }

  public void updateSeatStatus(String seatId, SeatStatus seatStatus) {
    var seat = seatRepository.findById(seatId)
        .orElseThrow(() -> new ResourceNotFoundException("Seat not found: " + seatId));
    seat = seat.toBuilder()
        .seatStatus(seatStatus)
        .updatedAt(Instant.now())
        .build();
    seatRepository.save(seat);
  }

  private Seat getSeat(SeatConfigRequest seatConfigRequest) {
    return Seat.builder()
        .id("SeatID_" + UUID.randomUUID())
        .seatNumber(seatConfigRequest.seatNumber())
        .seatType(seatConfigRequest.seatType())
        .price(seatConfigRequest.price())
        .seatStatus(seatConfigRequest.seatStatus())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  private  SeatConfigResponse getSeatConfigResponse(Seat savedSeat) {
    return SeatConfigResponse.builder()
        .seatId(savedSeat.getId())
        .seatNumber(savedSeat.getSeatNumber())
        .price(savedSeat.getPrice())
        .seatType(savedSeat.getSeatType())
        .seatStatus(savedSeat.getSeatStatus())
        .createdAt(savedSeat.getCreatedAt())
        .updatedAt(savedSeat.getUpdatedAt())
        .build();
  }
}
