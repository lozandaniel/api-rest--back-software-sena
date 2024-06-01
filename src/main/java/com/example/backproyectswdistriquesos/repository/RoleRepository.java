package com.example.backproyectswdistriquesos.repository;

import com.example.backproyectswdistriquesos.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
