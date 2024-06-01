package com.example.backproyectswdistriquesos.services;

import com.example.backproyectswdistriquesos.models.Product;
import com.example.backproyectswdistriquesos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    public ProductRepository productRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public Long getLengthProducts(){
        return productRepository.count();
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsWithoutInventory() {
        return productRepository.findByInventoryIsNull();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el producto"));
    }

    public void deleteProduct (Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product product){
        return productRepository.findById(id)
                .map(product1 -> {
                    if(product.getName() != null){
                        product1.setName(product.getName());
                    }
                    if(product.getDescription() != null){
                        product1.setDescription(product.getDescription());
                    }
                    if(product.getPrice() != null){
                        product1.setPrice(product.getPrice());
                    }
                    if(product.getCategory() != null){
                        product1.setCategory(product.getCategory());
                    }
                    if(product.getQuantity() != null){
                        product1.setQuantity(product.getQuantity());
                    }
                    return productRepository.save(product1);
                }).orElseThrow(() -> new RuntimeException("Producto no se encontro"));
    }

    public String subirImagen(MultipartFile file) {
        try {
            String cleanFilename = StringUtils.cleanPath(file.getOriginalFilename());
            Path path = Paths.get(uploadPath + cleanFilename);
            Files.copy(file.getInputStream(), path);

            String imageUrl = "/uploads/" + cleanFilename;
            return imageUrl;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}
