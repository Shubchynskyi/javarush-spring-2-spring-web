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
            orderRepo.save(build);
        }

    }
}
