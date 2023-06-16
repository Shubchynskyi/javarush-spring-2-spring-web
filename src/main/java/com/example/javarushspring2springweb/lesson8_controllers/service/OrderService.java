package com.example.javarushspring2springweb.lesson8_controllers.service;

import com.example.javarushspring2springweb.lesson8_controllers.entity.Product;
import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import com.example.javarushspring2springweb.lesson8_controllers.entity.Order;
import com.example.javarushspring2springweb.lesson8_controllers.repository.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final UserService userService;
    private final ProductService productService;

    public Optional<Order> get(Long id) {
        return Optional.of(orderRepo.getReferenceById(id));
    }
    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    public Optional<Order> create(String userId, String address, String[] productIds) {
        Optional<User> userOptional = userService.get(Long.parseLong(userId));
        List<Product> productList = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> product = productService.get(Long.valueOf(productId));
            product.ifPresent(productList::add);
        }

        User user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));

        Order build = Order.builder()
                .user(user)
                .address(address)
                .orderList(productList)
                .build();

        return Optional.of(orderRepo.save(build));
    }
}
