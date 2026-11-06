package com.myprojects.bookmyshow.application.api.entities;

import com.myprojects.bookmyshow.application.api.dto.SeatStatus;
import com.myprojects.bookmyshow.application.api.dto.SeatType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@Builder(toBuilder = true)
@Document(collection = "bms-seats")
public class Seat {
  @Id private String id;
  @Field("seatNumber") private String seatNumber;
  @Field("seatType") private SeatType seatType;
  @Field("price") private long price;
  @Field("seatStatus") private SeatStatus seatStatus;
  @Field("createdAt") private Instant createdAt;
  @Field("updatedAt") private Instant updatedAt;
}
