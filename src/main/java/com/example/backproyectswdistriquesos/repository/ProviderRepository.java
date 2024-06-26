package com.example.backproyectswdistriquesos.repository;

import com.example.backproyectswdistriquesos.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
