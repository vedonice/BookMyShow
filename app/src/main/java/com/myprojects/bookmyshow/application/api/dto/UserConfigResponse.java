package com.myprojects.bookmyshow.application.api.dto;

import lombok.Builder;

@Builder
public record UserConfigResponse(
    String name,
    long phone,
    int age,
    String email
){}