package com.example.backproyectswdistriquesos.controllers;

import com.example.backproyectswdistriquesos.models.Provider;
import com.example.backproyectswdistriquesos.models.ResponseMessage;
import com.example.backproyectswdistriquesos.services.ProviderService;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping
    public List<Provider> getAllProviders() {
        return providerService.getProviders();
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<?>> createNewProvider(@RequestBody Provider provider) {
        Provider newProvider = providerService.createProvider(provider);
        try{
            ResponseMessage<Provider> response =
                    new ResponseMessage<>(HttpStatus.CREATED.value(), "Usuario creado con exito", newProvider);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex){
            ResponseMessage<String> response =
                    new ResponseMessage<>(HttpStatus.BAD_REQUEST.value(), "Usuario no se guardo", ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ResponseMessage<?>> updateProvider(@PathVariable Long id, @RequestBody Provider provider) {
        Provider updateProvider = providerService.updateProvider(id, provider);
        try{
            ResponseMessage<Provider> response =
                    new ResponseMessage<>(HttpStatus.OK.value(), "Producto actualizado", updateProvider);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex){
            ResponseMessage<String> response =
                    new ResponseMessage<>
                            (HttpStatus.BAD_REQUEST.value(), "Error al actualizar el proveedor", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage<String>> createNewProvider(@PathVariable Long id) {
        providerService.deleteProvider(id);
        ResponseMessage<String> response =
                new ResponseMessage<>(HttpStatus.OK.value(), "Proveedor eliminado con exito" + id);
        return ResponseEntity.ok(response);
    }
}
