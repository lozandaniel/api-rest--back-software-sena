package com.example.backproyectswdistriquesos.controllers;

import com.example.backproyectswdistriquesos.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/dashboard/view")
public class DashboardAdmin {
    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderService orderService;


    @GetMapping
    public ResponseEntity<Map<String, Long>> getLengthsTables(){
        Map<String, Long> overview = new HashMap<>();
        overview.put("totalProducts", productService.getLengthProducts());
        overview.put("totalInventory", inventoryService.getLengthInventory());
        overview.put("totalProviders", providerService.getLengthProviders());
        overview.put("totalOrders", orderService.getLengthOrders());
        overview.put("totalClients", clientService.getLengthClients());
        return ResponseEntity.ok(overview);
    }
}
