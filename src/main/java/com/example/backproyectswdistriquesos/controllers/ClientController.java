package com.example.backproyectswdistriquesos.controllers;

import com.example.backproyectswdistriquesos.models.Provider;
import com.example.backproyectswdistriquesos.services.AuthService;
import com.example.backproyectswdistriquesos.exception.InvalidCredentialsException;
import com.example.backproyectswdistriquesos.models.Client;
import com.example.backproyectswdistriquesos.models.ResponseMessage;
import com.example.backproyectswdistriquesos.services.ClientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private AuthService authService;

    @GetMapping("clients")
    public List<Client> getAllClients() {
        return clientService.getClients();
    }

    @GetMapping("employees")
    public List<Client> getAllEmployees() {return clientService.getClientsByRolEmployee();}

    @GetMapping("clients/info")
    public ResponseEntity<?> getClient(HttpSession session) {

        Long clientId = (Long) session.getAttribute("clientId");
        if (clientId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
        }
        Client client = clientService.getClientById(clientId);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado");
        }
        return ResponseEntity.ok().body(client);
    }

    @DeleteMapping("clients/{id}")
    public ResponseEntity<ResponseMessage<String>> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        ResponseMessage<String> response =
                new ResponseMessage<>(HttpStatus.OK.value(), "Producto eliminado Id" + id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("clients/{id}/update")
    public ResponseEntity<ResponseMessage<?>> updateProvider(@PathVariable Long id, @RequestBody Client client) {
        Client updateClient = clientService.updateClient(id, client);
        try{
            ResponseMessage<Client> response =
                    new ResponseMessage<>(HttpStatus.OK.value(), "Cliente actualizado", updateClient);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex){
            ResponseMessage<String> response =
                    new ResponseMessage<>
                            (HttpStatus.BAD_REQUEST.value(), "Error al actualizar el cliente", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("user/register")
    public ResponseEntity<Client> userRegister (@RequestBody Client client) {
        Client registeredClient = clientService.userRegistration(client);
        return new ResponseEntity<>(registeredClient, HttpStatus.CREATED);
    }


    @PostMapping("user/login")
    public ResponseEntity<?> userLogin(@RequestBody Client client, HttpSession session){
        String email = client.getEmail();
        String password = client.getPassword();

        boolean infoClient = authService.authenticate(email, password);

        if (infoClient) {
            Long clientId = authService.getAuthenticatedUserId();
            Client dataClient = clientService.getClientById(clientId);

            if (dataClient != null){
                session.setAttribute("clientId", clientId);
                return ResponseEntity.ok().body(dataClient);
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al obtener información del cliente");
            }
        } else {
            throw new InvalidCredentialsException("Credenciales inválidas");
        }
    }

    @PostMapping("user/logout")
    public ResponseEntity<ResponseMessage<String>> logoutUser (HttpSession session) {
        authService.logout(session);
        ResponseMessage<String> response = new ResponseMessage<>(HttpStatus.OK.value(), "Sesión cerrada con exito");
        return ResponseEntity.ok(response);
    }

}
