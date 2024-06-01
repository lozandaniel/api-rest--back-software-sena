package com.example.backproyectswdistriquesos.repository;

import com.example.backproyectswdistriquesos.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmailAndPassword (String email, String password);
}
