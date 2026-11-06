package com.myprojects.bookmyshow.application.api.entities;

import com.myprojects.bookmyshow.application.api.dto.Screen;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@Document(collection = "bms-shows")
public class Show {
  @Id private String id;
  @Field("movie") private Movie movie;
  @Field("theater") private Theater theater;
  @Field("screens") private List<Screen> screens;
  @Field("startTime") private Instant startTime;
  @Field("endTime") private Instant endTime;
  @Field("createdAt") private Instant createdAt;
  @Field("updatedAt") private Instant updatedAt;
}
