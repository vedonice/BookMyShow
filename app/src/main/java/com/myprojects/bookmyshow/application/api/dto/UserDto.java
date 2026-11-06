package com.myprojects.bookmyshow.application.api.dto;

import lombok.Builder;

@Builder
public record UserDto(
    String name,
    long phone,
    int age,
    String email
) {}