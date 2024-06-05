package com.example.backproyectswdistriquesos.controllers;

import com.example.backproyectswdistriquesos.models.ResponseMessage;
import com.example.backproyectswdistriquesos.services.AuthService;
import com.example.backproyectswdistriquesos.models.Dto.OrderRequest;
import com.example.backproyectswdistriquesos.models.Order;
import com.example.backproyectswdistriquesos.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable Long orderId) {
        Long authenticatedUserId = authService.getAuthenticatedUserId();

        // Verificar si el pedido pertenece al usuario autenticado
        Order order = orderService.getOrder(orderId);
        System.out.println(order);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado");
        }

        if (!order.getClient().getClientId().equals(authenticatedUserId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario sin acceso");
        }

        return ResponseEntity.ok().body(order);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<?> getOrdersByClient (@PathVariable Long clientId) {
        Long authenticatedUserId = authService.getAuthenticatedUserId();
        if (!clientId.equals(authenticatedUserId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario sin acceso");
        }

        List<Order> clientOrders = orderService.getOrdersByClientId(clientId);
        return ResponseEntity.ok().body(clientOrders);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<?>> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            Order order =
                    orderService.createOrder(orderRequest.getClientId(),
                            orderRequest.getPaymentMethodId(),
                            orderRequest.getCartItems());
            ResponseMessage<Order> response =
                    new ResponseMessage<>
                            (HttpStatus.OK.value(), "Orden creada con exito", order);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            ResponseMessage<String> response =
                    new ResponseMessage<>
                            (HttpStatus.BAD_REQUEST.value(), "Error al actualizar el producto", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().body("Producto eliminado con exito " + id);
    }

}
