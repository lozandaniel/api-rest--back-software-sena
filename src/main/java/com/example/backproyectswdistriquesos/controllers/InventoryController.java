package com.example.backproyectswdistriquesos.controllers;

import com.example.backproyectswdistriquesos.models.Inventory;
import com.example.backproyectswdistriquesos.models.ResponseMessage;
import com.example.backproyectswdistriquesos.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("inventory")
    public List<Inventory> getAllInventory () {
        return inventoryService.getAllInventory();
    }

    @PostMapping("inventory/{id}")
    public ResponseEntity<ResponseMessage<?>> createInventory(@PathVariable Long id, @RequestBody Inventory inventoryDetails){
        Inventory updateInventory = inventoryService.createInventory(id, inventoryDetails);
        try{
            ResponseMessage<Inventory> response =
                    new ResponseMessage<>(HttpStatus.CREATED.value(), "Inventario exitosamente creado", updateInventory);
            return ResponseEntity.ok(response);
        }
        catch (RuntimeException ex){
            ResponseMessage<String> response =
                    new ResponseMessage<>
                            (HttpStatus.BAD_REQUEST.value(), "Error al actualizar el producto", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("inventory/{inventoryId}")
    public ResponseEntity<ResponseMessage<String>> deleteInventory(@PathVariable Long inventoryId) {
        inventoryService.deleteInventory(inventoryId);
        ResponseMessage<String> response =
                new ResponseMessage<>(HttpStatus.OK.value(), "Inventario eliminado Id: " + inventoryId);
        return ResponseEntity.ok(response);
    }
}
