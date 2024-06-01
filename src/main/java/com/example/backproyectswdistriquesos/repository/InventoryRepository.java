package com.example.backproyectswdistriquesos.repository;

import com.example.backproyectswdistriquesos.models.Inventory;
import com.example.backproyectswdistriquesos.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByProduct(Product product);
}
