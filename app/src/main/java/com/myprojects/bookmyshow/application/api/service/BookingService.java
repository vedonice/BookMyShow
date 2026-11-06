package com.myprojects.bookmyshow.application.api.service;

import com.myprojects.bookmyshow.application.api.dto.BookingRequest;
import com.myprojects.bookmyshow.application.api.dto.BookingResponse;
import com.myprojects.bookmyshow.application.api.dto.SeatConfigResponse;
import com.myprojects.bookmyshow.application.api.dto.ShowConfigResponse;
import com.myprojects.bookmyshow.application.api.entities.Booking;
import com.myprojects.bookmyshow.application.api.dto.BookingStatus;
import com.myprojects.bookmyshow.application.api.dto.SeatStatus;
import com.myprojects.bookmyshow.application.api.repository.BookingRepository;
import com.myprojects.bookmyshow.common.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookingService {
  private final BookingRepository bookingRepository;

  private final UserService userService;
  private final ShowService showService;
  private final SeatService seatService;

  public BookingResponse bookShow(BookingRequest bookingRequest) {
    var show = showService.getShowById(bookingRequest.showId());
    if (show == null) throw new ResourceNotFoundException("Show not found: " + bookingRequest.showId());

    var user = userService.getUserById(bookingRequest.userId());
    if (user == null) throw new ResourceNotFoundException("User not found: "+ bookingRequest.userId());

    var seat = seatService.getSeatById(bookingRequest.seatId());
    if (seat == null) throw new ResourceNotFoundException("Seat not found: "+ bookingRequest.seatId());
    if (seat.seatStatus() != SeatStatus.AVAILABLE) throw new RuntimeException("Seat not available");

    //lock the seat
    seatService.updateSeatStatus(bookingRequest.seatId(), SeatStatus.LOCKED);

    // create and save booking details
    var booking = getBooking(bookingRequest, show);
    var savedBooking = bookingRepository.save(booking);

    // return booking response
    return getBookingResponse(savedBooking, show, seat);
  }

  public void updateSeatStatus(String bookingId, SeatStatus seatStatus) {
    var booking = bookingRepository.findById(bookingId)
        .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + bookingId));

    seatService.updateSeatStatus(booking.getSeatId(), seatStatus);
  }

  private BookingResponse getBookingResponse(
      Booking savedBooking, ShowConfigResponse show, SeatConfigResponse seat) {
    var bookingStatus = (seat.seatStatus() == SeatStatus.BOOKED)
        ? BookingStatus.COMPLETED
        : BookingStatus.PENDING;

    return BookingResponse.builder()
        .bookingId(savedBooking.getId())
        .showId(savedBooking.getShowId())
        .showTime(show.startTime())
        .seatNumber(seat.seatNumber())
        .seatType(seat.seatType())
        .bookingStatus(bookingStatus)
        .createdAt(savedBooking.getCreatedAt())
        .updatedAt(savedBooking.getUpdatedAt())
        .build();
  }

  private Booking getBooking(BookingRequest bookingRequest, ShowConfigResponse show) {
    return Booking.builder()
        .id("BookingID_" + UUID.randomUUID())
        .showId(bookingRequest.showId())
        .seatId(bookingRequest.seatId())
        .userId(bookingRequest.userId())
        .status(BookingStatus.PENDING)
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }
}
