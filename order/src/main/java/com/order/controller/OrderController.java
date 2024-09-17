package com.order.controller;

import com.order.dto.Order;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired private OrderService orderService;
    @PostMapping("/save/order")
    public ResponseEntity<?> saveOrder(@RequestBody Order order) {

        return new ResponseEntity<>("Order placed Successfully", HttpStatus.CREATED);
    }
}
