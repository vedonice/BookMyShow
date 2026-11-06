package com.myprojects.bookmyshow.application.api.dto;

import java.util.List;

public record Screen(
    String id,
    String name,
    List<String> seats
) {}
