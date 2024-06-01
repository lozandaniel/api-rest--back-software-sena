package com.example.backproyectswdistriquesos.controllers;

import com.example.backproyectswdistriquesos.models.Dto.OrderRequest;
import com.example.backproyectswdistriquesos.models.Order;
import com.example.backproyectswdistriquesos.repository.OrderRepository;
import com.example.backproyectswdistriquesos.services.OrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getOrders();
    }

    public Order getOrder(@PathVariable Long id){
        return orderService.getOrder(id);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            Order order = orderService.createOrder(orderRequest.getClientId(), orderRequest.getCartItems());
            return ResponseEntity.ok().body(order);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
