package com.order.service;

import com.order.dto.Order;
import com.order.event.OrderCreatedEvent;
import com.order.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderService {
    @Value("${topic-name}") String TOPIC_NAME;
    KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public OrderService(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @Autowired
    OrderRepository orderRepository;
    public String saveOrder(Order order) {
        orderRepository.saveOrder(order);
        String orderId = UUID.randomUUID().toString();
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(orderId, order.getName(), order.getCost(), order.getCategory());
        CompletableFuture<SendResult<String, OrderCreatedEvent>> future = kafkaTemplate.send(TOPIC_NAME, orderId, orderCreatedEvent);
        future.whenComplete((res, ex) -> {
            if(ex==null) {
                System.out.println("Exception - "+ ex.getMessage()+" "+ex);
            } else{
                System.out.println("Result - " + res.getRecordMetadata());
            }
        });
        return orderId;
    }
}
