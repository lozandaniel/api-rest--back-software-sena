package com.example.backproyectswdistriquesos.services;

import com.example.backproyectswdistriquesos.models.Client;
import com.example.backproyectswdistriquesos.repository.ClientRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private HttpSession session;
    @Autowired
    private ClientRepository clientRepository;


    public boolean authenticate(String username, String password) {
        Client client = clientRepository.findByEmailAndPassword(username, password);
        if (client != null && client.getPassword().equals(password)) {
            // La autenticación es exitosa, almacenar información en la sesión si es necesario
            session.setAttribute("clientId", client.getClientId());
            return true; // Devuelve true para indicar una autenticación exitosa
        } else {
            // La autenticación falla, devuelve false
            return false;
        }
    }

    public Long getAuthenticatedUserId() {
        return (Long) session.getAttribute("clientId");
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }

}
