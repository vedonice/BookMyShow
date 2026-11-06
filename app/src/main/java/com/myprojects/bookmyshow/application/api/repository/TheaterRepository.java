package com.myprojects.bookmyshow.application.api.repository;

import com.myprojects.bookmyshow.application.api.entities.Theater;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends MongoRepository<Theater, String> {
}
