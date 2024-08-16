package com.example.flawtrack.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**")
//                .allowedOrigins("http://localhost:8080")
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("*")
//                .allowCredentials(true);
    	registry.addMapping("/**") // Allow all endpoints to be accessed
        .allowedOrigins("*") // Allow requests from any origin
        .allowedMethods("GET", "POST", "PUT", "DELETE"); // Allow specific methods
    }
}

