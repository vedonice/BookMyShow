package com.myprojects.bookmyshow.application.api.dto;

import com.myprojects.bookmyshow.application.api.entities.Show;
import lombok.Builder;

import java.util.List;

@Builder
public record TheaterDto(
    String name,
    String location,
    List<Show> shows
) {}
