package com.myprojects.bookmyshow.application.api.repository;

import com.myprojects.bookmyshow.application.api.entities.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
  Optional<Booking> findById(String id);
}
