package com.example.backproyectswdistriquesos.repository;

import com.example.backproyectswdistriquesos.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByInventoryIsNull();

    @Query("SELECT p FROM Product p JOIN FETCH p.provider")
    List<Product> findAllProductsWithProvider();
}
