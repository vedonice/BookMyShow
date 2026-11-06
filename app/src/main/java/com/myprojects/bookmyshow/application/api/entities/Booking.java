package com.myprojects.bookmyshow.application.api.entities;

import com.myprojects.bookmyshow.application.api.dto.BookingStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@Builder
@Document(collection = "bms-bookings")
public class Booking {
  @Id private String id;
  @Field("showId") private String showId;
  @Field("seatId") private String seatId;
  @Field("userId") private String userId;
  @Field("status") private BookingStatus status;
  @Field("createdAt") private Instant createdAt;
  @Field("updatedAt") private Instant updatedAt;
}
