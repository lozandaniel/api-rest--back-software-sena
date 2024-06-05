package com.example.backproyectswdistriquesos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Que rutas puede acceder el frontend
                .allowedOrigins("http://localhost:5173") // Origenes de donde se puede consumir la api
                .allowedMethods("*")// Que metodos sirven para el consumo del frontend
                .allowCredentials(true);
    }
}
