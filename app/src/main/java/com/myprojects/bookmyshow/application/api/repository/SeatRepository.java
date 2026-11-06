package com.myprojects.bookmyshow.application.api.repository;

import com.myprojects.bookmyshow.application.api.entities.Seat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SeatRepository extends MongoRepository<Seat, String> {
}
