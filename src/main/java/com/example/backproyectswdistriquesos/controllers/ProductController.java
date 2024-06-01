package com.example.backproyectswdistriquesos.controllers;

import com.example.backproyectswdistriquesos.models.Product;
import com.example.backproyectswdistriquesos.models.ResponseMessage;
import com.example.backproyectswdistriquesos.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("products")
    public List<Product> getAllProducts () {
        return productService.getProducts();
    }

    @GetMapping("products/inventory")
    public ResponseEntity<List<Product>> getProductsInInventory(){
        return ResponseEntity.ok(productService.getProductsWithoutInventory());
    }

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("products")
    public ResponseEntity<ResponseMessage<?>> createProduct(@RequestBody Product product){
        try {
            Product addedProduct = productService.createProduct(product);
            ResponseMessage<Product> response =
                    new ResponseMessage<>(HttpStatus.CREATED.value(), "Producto creado con éxito", addedProduct);
        return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            ResponseMessage<String> response =
                    new ResponseMessage<>
                            (HttpStatus.BAD_REQUEST.value(), "Error al actualizar el producto", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("products/{id}/update")
    public ResponseEntity<ResponseMessage<?>> updateProduct(@PathVariable Long id, @RequestBody Product product){
        try {
            Product updatedProduct = productService.updateProduct(id, product);
            ResponseMessage<Product> response =
                    new ResponseMessage<>(HttpStatus.OK.value(), "Producto actualizado con éxito", updatedProduct);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex){
            ResponseMessage<String> response =
                    new ResponseMessage<>
                            (HttpStatus.BAD_REQUEST.value(), "Error al actualizar el producto", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    @DeleteMapping("products/{productId}")
    public ResponseEntity<ResponseMessage<String>> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        ResponseMessage<String> response =
                new ResponseMessage<>(HttpStatus.OK.value(), "Producto eliminado Id: " + productId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String imageUrl = productService.subirImagen(file);
        if (imageUrl != null) {
            return ResponseEntity.ok(imageUrl);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
