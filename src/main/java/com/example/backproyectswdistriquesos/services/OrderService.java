package com.example.backproyectswdistriquesos.services;

import com.example.backproyectswdistriquesos.models.Client;
import com.example.backproyectswdistriquesos.models.Dto.CartItemRequest;
import com.example.backproyectswdistriquesos.models.Order;
import com.example.backproyectswdistriquesos.models.OrderItem;
import com.example.backproyectswdistriquesos.models.Product;
import com.example.backproyectswdistriquesos.repository.ClientRepository;
import com.example.backproyectswdistriquesos.repository.OrderRepository;
import com.example.backproyectswdistriquesos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Long getLengthOrders() {
        return orderRepository.count();
    }

    public List<Order> getOrders () {
        return orderRepository.findAll();
    }

    public Order getOrder(Long id){
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("La orden buscada no se encuentra disponible"));
    }

    public Order createOrder(Long clientId, List<CartItemRequest> cartItems){

        if (cartItems == null || cartItems.isEmpty()) {
            throw new RuntimeException("El carrito de compras no puede estar vacío.");
        }

        Order order = new Order();
        order.setClient(new Client(clientId));
        System.out.println(order.getClient());
        order.setStatus("Pendiente");

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0.0;

        for (CartItemRequest item : cartItems) {
            Product product = productRepository
                    .findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            System.out.println(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(product.getPrice());

            orderItems.add(orderItem);

            total += product.getPrice() * item.getQuantity();
        }
        order.setListOrderItems(orderItems);
        order.setTotal(total);
        return orderRepository.save(order);
    }
}
