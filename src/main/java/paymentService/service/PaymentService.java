package paymentService.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import paymentService.model.Order;

@Service
@Slf4j
public class PaymentService {

    private final KafkaTemplate<String, Order> kafkaTemplate;
    private final String payedOrdersTopic;

    public PaymentService(KafkaTemplate<String, Order> kafkaTemplate, @Value("${app.kafka.payedOrdersTopic}") String payedOrdersTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.payedOrdersTopic = payedOrdersTopic;
    }

    public void processPayment(Order order) {
        log.info("Processing payment for order: {}", order);
        order.setStatus("PAYED");
        kafkaTemplate.send(payedOrdersTopic, order);
    }
}
