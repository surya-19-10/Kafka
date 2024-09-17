package com.order.service;

import com.order.dto.Order;
import com.order.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Value("${topic-name}") String TOPIC_NAME;
    @Autowired KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    OrderRepository orderRepository;
    public void saveOrder(Order order) {
        Order savedOrder = orderRepository.saveOrder(order);
        if(savedOrder!=null) kafkaTemplate.send(TOPIC_NAME, order);
        else kafkaTemplate.send(TOPIC_NAME, null);
    }
}
