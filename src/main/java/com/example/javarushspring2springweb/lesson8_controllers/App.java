package com.example.javarushspring2springweb.lesson8_controllers;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
//        try (context) {
//            CommandResolver commandResolver = context.getBean(CommandResolver.class);
//            commandResolver.fillContextMap();
//            CommandResolver commandResolver = context.getBean(CommandResolver.class);
//
//            String[] names = context.getBeanDefinitionNames();
//            System.out.println("============= context =============");
//            Arrays.asList(names).forEach(System.out::println);
//            System.out.println("============= context =============");
//
//            System.err.println("TEST!!!!!!!!!!!!!!!!");
//            JavaConfigApplication.init();
//            UserService userService = JavaConfigApplication.getBean(UserService.class);
//
//            userService.create("username546", "userPass123");
//            System.out.println(userService.get(2L));
//        }
    }

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
