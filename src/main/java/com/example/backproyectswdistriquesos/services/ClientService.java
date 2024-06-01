package com.example.backproyectswdistriquesos.services;

import com.example.backproyectswdistriquesos.exception.InvalidCredentialsException;
import com.example.backproyectswdistriquesos.models.Client;
import com.example.backproyectswdistriquesos.models.Role;
import com.example.backproyectswdistriquesos.repository.ClientRepository;
import com.example.backproyectswdistriquesos.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Long getLengthClients() {
        return clientRepository.count();
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client updateClient(Long id, Client client) {
        return clientRepository.save(client);
    }

    public Client getClient(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("El usuario no se encuentra"));
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Transactional
    public Client userRegistration(Client client) {
        if (client.getRol() == null) {
            // Obtener el rol predeterminado solo si el cliente no tiene un rol asignado
            Role roleByDefault = roleRepository.findById(1L).orElseThrow(() -> new RuntimeException("Rol por defecto no encontrado"));
            client.setRol(roleByDefault);
        }

        System.out.println("Asignando el siguiente rol al cliente:");
        System.out.println("ID del rol: " + client.getRol());

        return clientRepository.save(client);
    }

    @Transactional
    public Client userLogin (String email, String password) {
        if (password.isEmpty() || email.isEmpty()) {
            throw new RuntimeException("Los campos no pueden estar vacios");
        }

        Client client = clientRepository.findByEmailAndPassword(email, password);
        if (client == null) {
            throw new InvalidCredentialsException("Credenciales inválidas");
        }
        return client;
    }
}
