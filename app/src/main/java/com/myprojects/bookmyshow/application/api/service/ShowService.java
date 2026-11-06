package com.myprojects.bookmyshow.application.api.service;

import com.myprojects.bookmyshow.application.api.dto.ShowConfigRequest;
import com.myprojects.bookmyshow.application.api.dto.ShowConfigResponse;
import com.myprojects.bookmyshow.application.api.entities.Movie;
import com.myprojects.bookmyshow.application.api.entities.Show;
import com.myprojects.bookmyshow.application.api.entities.Theater;
import com.myprojects.bookmyshow.application.api.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ShowService {
  private final ShowRepository showRepository;
  private final MovieService movieService;
  private final TheaterService theaterService;

  public ShowService(ShowRepository showRepository, MovieService movieService, TheaterService theaterService) {
    this.showRepository = showRepository;
    this.movieService = movieService;
    this.theaterService = theaterService;
  }

  public ShowConfigResponse createShow(ShowConfigRequest showConfigRequest) {
    Show show = getShowConfig(showConfigRequest);
    Show saved = showRepository.save(show);
    return getShowConfigResponse(saved);
  }

  public List<ShowConfigResponse> getAllShows() {
    return showRepository.findAll().stream()
        .map(this::getShowConfigResponse)
        .toList();
  }

  public ShowConfigResponse getShowById(String showId) {
    Show show = showRepository.findById(showId)
        .orElseThrow(() -> new RuntimeException("Show not found: "+ showId));
    return getShowConfigResponse(show);
  }

  private Show getShowConfig(ShowConfigRequest showConfigRequest) {
    var movieConfigResponse = movieService.getMovieById(showConfigRequest.movieId());
    var movie = Movie.builder()
        .id(movieConfigResponse.movieId())
        .title(movieConfigResponse.title())
        .description(movieConfigResponse.description())
        .language(movieConfigResponse.language())
        .duration(movieConfigResponse.duration())
        .movieStatus(movieConfigResponse.movieStatus())
        .releaseDate(movieConfigResponse.releaseDate())
        .createdAt(movieConfigResponse.createdAt())
        .updatedAt(movieConfigResponse.updatedAt())
        .build();

    var theaterConfigResponse = theaterService.getTheaterById(showConfigRequest.theaterId());
    var theater = Theater.builder()
        .id(showConfigRequest.theaterId())
        .name(theaterConfigResponse.name())
        .location(theaterConfigResponse.location())
        .createdAt(theaterConfigResponse.createdAt())
        .updatedAt(theaterConfigResponse.updatedAt())
        .build();

    return Show.builder()
        .id("ShowID_" + UUID.randomUUID())
        .movie(movie)
        .theater(theater)
        .screens(showConfigRequest.screens())
        .startTime(showConfigRequest.startTime())
        .endTime(showConfigRequest.endTime())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  private ShowConfigResponse getShowConfigResponse(Show show) {
    return ShowConfigResponse.builder()
        .showId(show.getId())
        .movieId(show.getMovie().getId())
        .theaterId(show.getTheater().getId())
        .screens(show.getScreens())
        .startTime(show.getStartTime())
        .endTime(show.getEndTime())
        .createdAt(show.getCreatedAt())
        .updatedAt(show.getUpdatedAt())
        .build();
  }
}
