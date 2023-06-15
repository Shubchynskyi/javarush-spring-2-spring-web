package com.example.javarushspring2springweb.lesson8_controllers;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Map;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

//    @Bean
//    Map<String, String> views() {
//        return Map.of(
//                "", "WEB-INF/index.jsp",
//                "/users", "WEB-INF/users.jsp",
//                "/user", "WEB-INF/user.jsp",
//                "/orders", "WEB-INF/orders.jsp",
//                "/order", "WEB-INF/order.jsp",
//                "/products", "WEB-INF/products.jsp",
//                "/product", "WEB-INF/product.jsp"
//        );
    }

}
