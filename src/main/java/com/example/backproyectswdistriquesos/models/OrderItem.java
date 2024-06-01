package com.example.backproyectswdistriquesos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "pedido_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_items")
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Product product;

    @Column(name = "cantidad")
    private int quantity;

    @Column(name = "precio")
    private Double price;

    public OrderItem() {}

    public OrderItem(Long orderItemId, Order order, Product product, int quantity, Double price) {
        this.orderItemId = orderItemId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
