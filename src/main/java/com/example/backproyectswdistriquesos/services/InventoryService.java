package com.example.backproyectswdistriquesos.services;

import com.example.backproyectswdistriquesos.models.Inventory;
import com.example.backproyectswdistriquesos.models.Product;
import com.example.backproyectswdistriquesos.repository.InventoryRepository;
import com.example.backproyectswdistriquesos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Long getLengthInventory() {
        return inventoryRepository.count();
    }

    public List<Inventory> getAllInventory (){
        return inventoryRepository.findAll();
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    public Inventory createInventory (Long productId, Inventory inventoryDetails) {
        Product findProduct = productRepository
                .findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Inventory inventory = inventoryRepository.findByProduct(findProduct);

        if (inventory != null) {
            throw new RuntimeException("Producto ya cuenta con inventario existente");
        } else {
            inventory = new Inventory();
            inventory.setProduct(findProduct);
        }

        inventory.setStock(inventoryDetails.getStock());
        inventory.setClassification(inventoryDetails.getClassification());
        inventory.setBatch(inventoryDetails.getBatch());
        inventory.setStock(inventoryDetails.getStock());
        inventory.setMinStock(inventoryDetails.getMinStock());
        inventory.setMaxStock(inventoryDetails.getMaxStock());
        inventory.setBuyPrice(inventoryDetails.getBuyPrice());
        inventory.setSellPrice(inventoryDetails.getSellPrice());
        inventory.setStatus(inventoryDetails.getStatus());

        return inventoryRepository.save(inventory);
    }



}
