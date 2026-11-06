package com.myprojects.bookmyshow.domain.publisher;

import com.myprojects.bookmyshow.common.model.PaymentRequestEvent;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventPublisher {

  private final KafkaTemplate<String, PaymentRequestEvent> kafkaTemplate;

  PaymentEventPublisher(KafkaTemplate<String, PaymentRequestEvent> kafkaTemplate, KafkaProperties kafkaProperties) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void publish(PaymentRequestEvent paymentRequestEvent){
    String tenant = "bookmyshow";
    kafkaTemplate.send(tenant + ".paymentRequestTopic", paymentRequestEvent);
  }
}
