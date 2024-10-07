package com.ecommerce.app.controller;

import com.ecommerce.app.dto.OrderDTO;
import com.ecommerce.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/api/orders")
    public ResponseEntity<String> placeOrder(@RequestBody OrderDTO orderDTO){
        return orderService.placeOrder(orderDTO);
    }


}
