package org.example.authservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // You can specify the origins that are allowed to access your resources
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // You can specify the HTTP methods allowed
                .allowedHeaders("*"); // You can specify the HTTP headers allowed
    }
}