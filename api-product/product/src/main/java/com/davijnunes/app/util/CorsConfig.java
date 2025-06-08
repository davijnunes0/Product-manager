package com.davijnunes.app.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS (Cross-Origin-Sharing) é um mecanismo de segurança dos navegadores que controla como um aplicativo web em um domínio pode acessar 
 * recursos de outro domínio. È uma política de segurança implementada por navegadores para proteger os usuários.
 */
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                    // Quais origens podem acessar.
                    .allowedOrigins("http://127.0.0.1:5500", "http://localhost:5500")
                    // Métodos HTTP permitidos.
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true)
                    .maxAge(3600);
            }
        };
    }
}