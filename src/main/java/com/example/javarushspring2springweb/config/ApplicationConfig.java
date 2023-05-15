package com.example.javarushspring2springweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@ComponentScan("com.example.javarushspring2springweb")
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Bean
    Map<String, String> views() {
        return Map.of(
                "", "WEB-INF/index.jsp",
                "/users", "WEB-INF/users.jsp",
                "/user", "WEB-INF/user.jsp",
                "/orders", "WEB-INF/orders.jsp",
                "/order", "WEB-INF/order.jsp",
                "/products", "WEB-INF/products.jsp",
                "/product", "WEB-INF/product.jsp"
        );
    }
}
