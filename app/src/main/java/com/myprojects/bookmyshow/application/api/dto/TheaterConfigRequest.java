package com.myprojects.bookmyshow.application.api.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TheaterConfigRequest(
    String name,
    String location,
    List<Screen> screens
) {}