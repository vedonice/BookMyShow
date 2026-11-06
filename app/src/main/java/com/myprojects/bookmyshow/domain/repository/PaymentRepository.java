package com.myprojects.bookmyshow.domain.repository;

import com.myprojects.bookmyshow.domain.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
  Payment findByTransactionId(String transactionId);
}
