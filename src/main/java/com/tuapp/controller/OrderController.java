package com.tuapp.controller;

import com.tuapp.model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping
    public String createOrder(@RequestBody OrderRequest request) {
        Long orderId = System.currentTimeMillis();
        publisher.publishEvent(new OrderCreatedEvent(this, orderId, request.getEmail(), request.getProducts()));
        return "Orden recibida. ID: " + orderId;
    }
}