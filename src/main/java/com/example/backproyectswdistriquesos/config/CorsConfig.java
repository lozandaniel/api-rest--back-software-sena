package com.example.backproyectswdistriquesos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Que rutas puede acceder el frontend
                .allowedOrigins("*") // Origenes de donde se puede consumir la api
                .allowedMethods("*"); // Que metodos sirven para el consumo del frontend
    }
}
