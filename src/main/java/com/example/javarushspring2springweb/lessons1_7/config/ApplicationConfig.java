package com.example.javarushspring2springweb.lessons1_7.config;

import org.springframework.context.annotation.*;

import java.util.Map;

@Configuration
@ComponentScan("com.example.javarushspring2springweb")
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy
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
