package com.example.backproyectswdistriquesos.models;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long rolId;
    @Column(name = "rol")
    private String rol;

    public Role() {
    }

    public Role(Long rolId, String rol) {
        this.rolId = rolId;
        this.rol = rol;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
