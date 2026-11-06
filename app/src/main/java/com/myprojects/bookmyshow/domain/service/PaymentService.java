package com.myprojects.bookmyshow.domain.service;

import com.myprojects.bookmyshow.common.exception.ResourceNotFoundException;
import com.myprojects.bookmyshow.domain.dto.PaymentDto;
import com.myprojects.bookmyshow.common.model.PaymentRequestEvent;
import com.myprojects.bookmyshow.domain.dto.PaymentStatus;
import com.myprojects.bookmyshow.domain.dto.PaymentStatusResponse;
import com.myprojects.bookmyshow.domain.publisher.PaymentEventPublisher;
import com.myprojects.bookmyshow.domain.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class PaymentService {

  private final PaymentRepository paymentRepository;
  private final PaymentEventPublisher paymentEventPublisher;

  public PaymentStatusResponse initiatePayment(PaymentDto paymentDto) {
    var payment = PaymentDto.toPayment(paymentDto, PaymentStatus.INITIATED);
    var savedPayment = paymentRepository.save(payment);

    var paymentEvent = PaymentRequestEvent.builder()
        .userId(savedPayment.userId())
        .bankName(paymentDto.bankName())
        .accountNumber(paymentDto.accountNumber())
        .amount(paymentDto.amount())
        .bookingId(savedPayment.bookingId())
        .transactionId(savedPayment.transactionId())
        .paymentStatus(PaymentStatus.INITIATED)
        .createdAt(Instant.now())
        .build();

    paymentEventPublisher.publish(paymentEvent);

    return PaymentStatusResponse.builder()
        .transactionId(savedPayment.transactionId())
        .bookingId(savedPayment.bookingId())
        .paymentStatus(PaymentStatus.INITIATED)
        .build();
  }

  public void updatePaymentStatus(String transactionId){
    var payment = paymentRepository.findByTransactionId(transactionId);
    if (payment == null) throw new ResourceNotFoundException("Payment not found");

    payment = payment.toBuilder()
    .paymentStatus(PaymentStatus.COMPLETED)
    .updatedAt(Instant.now())
    .build();

    paymentRepository.save(payment);
  }
}
