package com.example.backproyectswdistriquesos.services;

import com.example.backproyectswdistriquesos.models.Role;
import com.example.backproyectswdistriquesos.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    public RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
