package com.example.backproyectswdistriquesos.repository;

import com.example.backproyectswdistriquesos.models.Client;
import com.example.backproyectswdistriquesos.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmailAndPassword (String email, String password);

    List<Client> findByRolRol(String rol);
}
