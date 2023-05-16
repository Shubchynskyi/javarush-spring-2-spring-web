package com.example.javarushspring2springweb.service;

import com.example.javarushspring2springweb.entity.Product;
import com.example.javarushspring2springweb.entity.User;
import com.example.javarushspring2springweb.repository.AbstractRepo;
import com.example.javarushspring2springweb.entity.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class OrderService {
    private final AbstractRepo<Order> orderRepo;
    private final UserService userService;
    private final ProductService productService;

    public Optional<Order> get(Long id) {
        return Optional.of(orderRepo.getById(id));
    }
    public List<Order> getAll() {
        return orderRepo.getAll();
    }

    public void create(String userId, String address, String[] productIds) {
        Optional<User> userOptional = userService.get(Long.parseLong(userId));
        List<Product> productList = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> product = productService.get(Long.valueOf(productId));
            product.ifPresent(productList::add);
        }
        if(userOptional.isPresent()) {
            Order build = Order.builder()
                    .user(userOptional.get())
                    .address(address)
                    .orderList(productList)
                    .build();
            orderRepo.create(build);
        }

    }
}
