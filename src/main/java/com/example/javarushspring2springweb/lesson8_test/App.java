package com.example.javarushspring2springweb.lesson8_test;

import com.example.javarushspring2springweb.lesson8_test.entity.Customer;
import com.example.javarushspring2springweb.lesson8_test.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class App {

//    private static ConfigurableApplicationContext context = SpringApplication.run(App.class);

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
    public static void main(String[] args) {
        var context = SpringApplication.run(App.class, args);
        try(context) {
            long id = 5L;
            CustomerService customerService = context.getBean(CustomerService.class);
            Customer customer = Customer.builder().login("CusLogin2").password("superPass").build();
            customerService.update(customer);

            customer = customerService.get(id).orElseThrow();
            System.out.println(customerService.get(id).orElseThrow());

            customer.setPassword("test12345");
            customerService.update(customer);
            System.out.println(customerService.get(id).orElseThrow());

            customer.setPassword("456");
            customerService.update(customer);
            System.out.println(customerService.get(id).orElseThrow());
        }
    }
}
