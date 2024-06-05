package com.example.backproyectswdistriquesos.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long clientId;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Role rol;
    @Column(name = "nombre")
    private String name;
    @Column(name = "contraseña")
    private String password;
    @Column(name = "identificacion")
    private Integer identification;
    @Column(name = "telefono")
    private Integer phone;
    @Column(name = "correo_electronico")
    private String email;
    @Column(name = "direccion")
    private String direction;

    @CreationTimestamp
    @Column(name = "fecha_registro")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerDate;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Order> orders;

    public Client(){}

    public Client(String email, String password){
        this.email = email;
        this.password = password;
    }

    public Client(Long clientId){
        this.clientId = clientId;
    }

    public Client(Long clientId, Role rol, String name, String password, Integer identification, Integer phone, String email, String direction, LocalDateTime registerDate, List<Order> orders) {
        this.clientId = clientId;
        this.rol = rol;
        this.name = name;
        this.password = password;
        this.identification = identification;
        this.phone = phone;
        this.email = email;
        this.direction = direction;
        this.registerDate = registerDate;
        this.orders = orders;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdentification() {
        return identification;
    }

    public void setIdentification(Integer identification) {
        this.identification = identification;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
