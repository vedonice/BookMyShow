package com.myprojects.bookmyshow.application.api.dto;

public record User(
    String id,
    String name,
    long phone,
    int age,
    String email
) {}