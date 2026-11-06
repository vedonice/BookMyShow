package com.myprojects.bookmyshow.application.consumer;

import com.myprojects.bookmyshow.application.api.dto.SeatStatus;
import com.myprojects.bookmyshow.application.api.service.BookingService;
import com.myprojects.bookmyshow.common.exception.ResourceNotFoundException;
import com.myprojects.bookmyshow.common.model.PaymentResponseEvent;
import com.myprojects.bookmyshow.domain.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentResponseConsumer {

  private final PaymentService paymentService;
  private final BookingService bookingService;

  @KafkaListener(
      topics = "bookmyshow.paymentResponseTopic",
      groupId = "paymentResponseGroup",
      properties = {
          "key.deserializer=org.apache.kafka.common.serialization.StringDeserializer",
          "value.deserializer=org.springframework.kafka.support.serializer.JsonDeserializer",
          "spring.json.trusted.packages=com.myprojects.bookmyshow.*",
          "spring.json.value.default.type=com.myprojects.bookmyshow.common.model.PaymentResponseEvent",
          "spring.json.use.type.headers=false",
          "auto.offset.reset=latest"
      })
  public void consume(PaymentResponseEvent paymentResponseEvent) {
    System.out.println("PaymentResponseConsumer: " + paymentResponseEvent);
    try {
      // update payment status
      paymentService.updatePaymentStatus(paymentResponseEvent.transactionId());
      // update seat status
      bookingService.updateSeatStatus(paymentResponseEvent.bookingId(), SeatStatus.BOOKED);
      System.out.println("Booking ID: " + paymentResponseEvent.bookingId() + "Confirmed !! Enjoy the show !!");

    } catch (ResourceNotFoundException e) {
      System.out.println("Failed processing payment response. Reason: "+ e.getMessage());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
