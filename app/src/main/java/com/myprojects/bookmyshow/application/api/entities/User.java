package com.myprojects.bookmyshow.application.api.entities;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Builder
@Document(collection = "bms-users")
public class User {
  @Id private String id;
  @Field("name") private String name;
  @Field("phone") private long phone;
  @Field("age") private int age;
  @Field("email") private String email;
}
