package com.myprojects.bookmyshow.application.api.controller;

import com.myprojects.bookmyshow.application.api.dto.*;
import com.myprojects.bookmyshow.application.api.service.BookingService;
import com.myprojects.bookmyshow.application.api.service.MovieService;
import com.myprojects.bookmyshow.application.api.service.TheaterService;
import com.myprojects.bookmyshow.application.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/bookmyshow/user")
public class UserController {

  private final UserService userService;
  private final MovieService movieService;
  private final TheaterService theaterService;
  private final BookingService bookingService;


  @PostMapping("/config")
  public ResponseEntity<UserConfigResponse> createUserConfig(@RequestBody UserConfigRequest userConfigRequest) {
    return ResponseEntity.ok(userService.createUserConfig(userConfigRequest));
  }

  @GetMapping("/movies")
  public ResponseEntity<List<MovieDto>> getAllMoviesToWatch() {
    return ResponseEntity.ok(movieService.getAllMoviesToWatch());
  }

  @PostMapping("/book/show")
  public ResponseEntity<BookingResponse> bookShow(@RequestBody BookingRequest bookingRequest) {
    return ResponseEntity.ok(bookingService.bookShow(bookingRequest));
  }
}
