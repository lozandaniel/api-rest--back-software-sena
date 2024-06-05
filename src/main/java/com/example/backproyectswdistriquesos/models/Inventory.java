package com.example.backproyectswdistriquesos.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventario")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private Long inventoryId;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Product product;

    @Column(name = "existencias")
    private int stock;

    @Column(name = "clasificacion")
    private String classification;

    @Column(name = "lote")
    private int batch;

    @Column(name = "stock_min")
    private int minStock;

    @Column(name = "stock_max")
    private Integer maxStock;

    @Column(name = "estado")
    private String status;

    @Column(name = "precio_compra")
    private double buyPrice;

    @Column(name = "precio_venta")
    private double sellPrice;

    @CreationTimestamp
    @Column(name = "fecha")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    public Inventory(){}

    public Inventory(Long inventoryId, Product product, int stock, String classification, int batch, int minStock, Integer maxStock, String status, LocalDateTime date) {
        this.inventoryId = inventoryId;
        this.product = product;
        this.stock = stock;
        this.classification = classification;
        this.batch = batch;
        this.minStock = minStock;
        this.maxStock = maxStock;
        this.status = status;
        this.date = date;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public Integer getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(Integer maxStock) {
        this.maxStock = maxStock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
