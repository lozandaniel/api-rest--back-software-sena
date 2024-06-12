package com.example.backproyectswdistriquesos.services;

import com.example.backproyectswdistriquesos.exception.InvalidCredentialsException;
import com.example.backproyectswdistriquesos.models.Client;
import com.example.backproyectswdistriquesos.models.Provider;
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

    public List<Client> getClientsByRolEmployee() {return clientRepository.findByRolRol("empleado");}

    public Client getClientById(Long id) {
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

    public Client updateClient (Long id, Client client) {
        return clientRepository
                .findById(id)
                .map(client1 -> {
                    if (client.getName() != null) {
                        client1.setName(client.getName());
                    }
                    if (client.getIdentification() != null) {
                        client1.setIdentification(client.getIdentification());
                    }
                    if (client.getRol() != null) {
                        client1.setRol(client.getRol());
                    }
                    if (client.getPhone() != null) {
                        client1.setPhone(client.getPhone());
                    }
                    if (client.getDirection() != null) {
                        client1.setDirection(client.getDirection());
                    }
                    if(client.getPassword() != null){
                        client1.setPassword(client.getPassword());
                    }
                    if (client.getEmail() != null) {
                        client1.setEmail(client.getEmail());
                    }
                    return clientRepository.save(client1);
                }).orElseThrow(() -> new RuntimeException("Cliente no se encuentra"));
    }
}
