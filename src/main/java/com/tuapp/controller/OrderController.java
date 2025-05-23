package com.tuapp.controller;

import com.tuapp.model.OrderRequest;
import com.tuapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public String createOrder(@RequestBody OrderRequest request) {
        return orderService.processOrder(request);
    }
}
