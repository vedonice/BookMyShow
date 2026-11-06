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
@Document(collection = "bms-theaters")
public class Theater {
  @Id private String id;
  @Field("name") private String name;
  @Field("location") private String location;
  @Field("screens") private List<Screen> screens;
  @Field("createdAt") private Instant createdAt;
  @Field("updatedAt") private Instant updatedAt;
}
