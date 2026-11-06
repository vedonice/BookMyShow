package com.myprojects.bookmyshow.application.api.controller;

import com.myprojects.bookmyshow.application.api.dto.*;
import com.myprojects.bookmyshow.application.api.service.MovieService;
import com.myprojects.bookmyshow.application.api.service.SeatService;
import com.myprojects.bookmyshow.application.api.service.ShowService;
import com.myprojects.bookmyshow.application.api.service.TheaterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/bookmyshow/admin")
public class AdminController {

  private final MovieService movieService;
  private final TheaterService theaterService;
  private final ShowService showService;
  private  final SeatService seatService;

  public AdminController(MovieService movieService, TheaterService theaterService, ShowService showService, SeatService seatService) {
    this.movieService = movieService;
    this.theaterService = theaterService;
    this.showService = showService;
    this.seatService = seatService;
  }

  @PostMapping("/movie/config")
  public ResponseEntity<MovieConfigResponse> createMovieConfig(@RequestBody MovieConfigRequest movieConfigRequest) {
    return ResponseEntity.ok(movieService.createMovie(movieConfigRequest));
  }

  @GetMapping("/movies")
  public ResponseEntity<List<MovieConfigResponse>> getAllMoviesConfig() {
    return ResponseEntity.ok(movieService.getAllMovies());
  }

  @PostMapping("/seat/config")
  public ResponseEntity<SeatConfigResponse> createSeatConfig(@RequestBody SeatConfigRequest seatConfigRequest) {
    return ResponseEntity.ok(seatService.createSeat(seatConfigRequest));
  }

  @PostMapping("/theater/config")
  public  ResponseEntity<TheaterConfigResponse> createTheaterConfig(@RequestBody TheaterConfigRequest theaterConfigRequest) {
    return ResponseEntity.ok(theaterService.createTheater(theaterConfigRequest));
  }

  @GetMapping("/theaters")
  public ResponseEntity<List<TheaterConfigResponse>> getAllTheatersConfig() {
    return ResponseEntity.ok(theaterService.getAllTheaters());
  }

  @PostMapping("/show/config")
  public ResponseEntity<ShowConfigResponse> createShowConfig(@RequestBody ShowConfigRequest showConfigRequest) {
    return ResponseEntity.ok(showService.createShow(showConfigRequest));
  }

  @GetMapping("/shows")
  public ResponseEntity<List<ShowConfigResponse>> getAllShowsConfig() {
    return ResponseEntity.ok(showService.getAllShows());
  }
}
