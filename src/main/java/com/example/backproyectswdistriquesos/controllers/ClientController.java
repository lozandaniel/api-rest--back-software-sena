package com.example.backproyectswdistriquesos.controllers;

import com.example.backproyectswdistriquesos.models.Client;
import com.example.backproyectswdistriquesos.models.ResponseMessage;
import com.example.backproyectswdistriquesos.services.ClientService;
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

    @GetMapping("clients")
    public List<Client> getAllClients() {
        return clientService.getClients();
    }

    @GetMapping("clients/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        Client client = clientService.getClient(id);
        return ResponseEntity.ok().body(client);
    }

    @DeleteMapping("clients/{id}")
    public ResponseEntity<ResponseMessage<String>> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        ResponseMessage<String> response =
                new ResponseMessage<>(HttpStatus.OK.value(), "Producto eliminado Id" + id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("user/register")
    public ResponseEntity<Client> userRegister (@RequestBody Client client) {
        Client registeredClient = clientService.userRegistration(client);
        return new ResponseEntity<>(registeredClient, HttpStatus.CREATED);
    }


    @PostMapping("user/login")
    public ResponseEntity<?> userLogin(@RequestBody Client client){
        String email = client.getEmail();
        String password = client.getPassword();
        Object infoClient = clientService.userLogin(email, password);
        return ResponseEntity.ok().body(infoClient);
    }

}
