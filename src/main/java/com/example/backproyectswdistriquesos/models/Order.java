package com.example.backproyectswdistriquesos.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Client client;

    @CreationTimestamp
    @Column(name = "fecha_compra")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime buyDate = LocalDateTime.now();

    @Column(name = "total")
    private Double total;

    @Column(name = "estado")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_medio_de_pago", referencedColumnName = "id_medio_de_pago")
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItem> listOrderItems;

    public Order(){}

    public Order(Long orderId, Client client, LocalDateTime buyDate, Double total, String status, PaymentMethod paymentMethod, List<OrderItem> listOrderItems) {
        this.orderId = orderId;
        this.client = client;
        this.buyDate = buyDate;
        this.total = total;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.listOrderItems = listOrderItems;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDateTime buyDate) {
        this.buyDate = buyDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getListOrderItems() {
        return listOrderItems;
    }

    public void setListOrderItems(List<OrderItem> listOrderItems) {
        this.listOrderItems = listOrderItems;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
