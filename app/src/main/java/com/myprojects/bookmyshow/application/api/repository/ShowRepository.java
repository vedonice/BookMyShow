package com.myprojects.bookmyshow.application.api.repository;

import com.myprojects.bookmyshow.application.api.entities.Show;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShowRepository extends MongoRepository<Show, String> {
}
