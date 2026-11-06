package com.myprojects.bookmyshow.application.api.entities;

import com.myprojects.bookmyshow.application.api.dto.MovieStatus;
import lombok.Builder;
import lombok.Getter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.Instant;

@Getter
@Builder
@Document(collection = "bms-movies")
public class Movie {
  @Id private String id;
  @Field("title") private String title;
  @Field("description") private String description;
  @Field("duration") private Long duration;
  @Field("language") private String language;
  @Field("movieStatus") private MovieStatus movieStatus;
  @Field("releaseDate") private Instant releaseDate;
  @Field("createdAt") private Instant createdAt;
  @Field("updatedAt") private Instant updatedAt;
}
