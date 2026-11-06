package com.myprojects.bookmyshow.application.api.repository;

import com.myprojects.bookmyshow.application.api.entities.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
}
