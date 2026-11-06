package com.myprojects.bookmyshow.application.api.service;

import com.myprojects.bookmyshow.application.api.dto.MovieConfigRequest;
import com.myprojects.bookmyshow.application.api.dto.MovieConfigResponse;
import com.myprojects.bookmyshow.application.api.dto.MovieDto;
import com.myprojects.bookmyshow.application.api.entities.Movie;
import com.myprojects.bookmyshow.application.api.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class MovieService {

  private final MovieRepository movieRepository;

  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public MovieConfigResponse createMovie(MovieConfigRequest request) {
    Movie movie = getMovie(request);
    Movie saved = movieRepository.save(movie);
    return getMovieConfigResponse(saved);
  }

  public MovieConfigResponse getMovieById(String movieId) {
    return movieRepository.findById(movieId)
        .map(this::getMovieConfigResponse)
        .orElseThrow(() -> new RuntimeException("Movie not found"));
  }

  public List<MovieConfigResponse> getAllMovies() {
    return movieRepository.findAll().stream()
        .map(movie -> MovieConfigResponse.builder()
            .title(movie.getTitle())
            .movieId(movie.getId())
            .movieStatus(movie.getMovieStatus())
            .description(movie.getDescription())
            .language(movie.getLanguage())
            .duration(movie.getDuration())
            .releaseDate(movie.getReleaseDate())
            .createdAt(movie.getCreatedAt())
            .updatedAt(movie.getUpdatedAt())
            .build())
        .toList();
  }

  public List<MovieDto> getAllMoviesToWatch() {
    return movieRepository.findAll().stream()
        .map(movie -> MovieDto.builder()
            .title(movie.getTitle())
            .description(movie.getDescription())
            .movieStatus(movie.getMovieStatus())
            .releaseDate(movie.getReleaseDate())
            .language(movie.getLanguage())
            .duration(movie.getDuration())
            .build())
        .toList();
  }

  private MovieConfigResponse getMovieConfigResponse(Movie saved) {
    return MovieConfigResponse.builder()
        .title(saved.getTitle())
        .movieId(saved.getId())
        .language(saved.getLanguage())
        .duration(saved.getDuration())
        .releaseDate(saved.getReleaseDate())
        .movieStatus(saved.getMovieStatus())
        .createdAt(saved.getCreatedAt())
        .updatedAt(saved.getUpdatedAt())
        .build();
  }

  private Movie getMovie(MovieConfigRequest request) {
    Instant now = Instant.now();
    return Movie.builder()
        .id("MovieID_" + UUID.randomUUID())
        .title(request.title())
        .description(request.description())
        .duration(request.duration())
        .language(request.language())
        .movieStatus(request.movieStatus())
        .releaseDate(request.releaseDate())
        .createdAt(now)
        .updatedAt(now)
        .build();
  }
}
