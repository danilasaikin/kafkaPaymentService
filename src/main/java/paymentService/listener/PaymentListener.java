package paymentService.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import paymentService.model.Order;
import paymentService.service.PaymentService;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentListener {

    private final PaymentService paymentService;

    @KafkaListener(topics = "${app.kafka.newOrdersTopic}", groupId = "${app.kafka.groupId}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(Order order) {
        log.info("Received new order: {}", order);
        paymentService.processPayment(order);
    }
}
