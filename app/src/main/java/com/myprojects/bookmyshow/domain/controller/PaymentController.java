package com.myprojects.bookmyshow.domain.controller;

import com.myprojects.bookmyshow.common.model.Status;
import com.myprojects.bookmyshow.common.model.StatusCode;
import com.myprojects.bookmyshow.domain.dto.PaymentDto;
import com.myprojects.bookmyshow.domain.dto.PaymentResponse;
import com.myprojects.bookmyshow.domain.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/bookmyshow/payment")
public class PaymentController {

  private final PaymentService paymentService;

  @PostMapping("/initiate")
  public ResponseEntity<PaymentResponse> initiatePayment(@RequestBody PaymentDto paymentDto) {
    PaymentResponse paymentResponse = null;
    try {
      var paymentStatusResponse = paymentService.initiatePayment(paymentDto);
      if (paymentStatusResponse != null) {
        paymentResponse = PaymentResponse.builder()
            .status(Status.SUCCESS)
            .statusCode(StatusCode.CREATED)
            .statusMessage("Payment initiated successfully")
            .paymentStatusResponse(paymentStatusResponse)
            .build();
      }
    } catch (Exception e) {
      paymentResponse = PaymentResponse.builder()
          .status(Status.FAILED)
          .statusCode(StatusCode.INTERNAL_SERVER_ERROR)
          .statusMessage(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(paymentResponse);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(paymentResponse);
  }
}
