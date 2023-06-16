package com.example.javarushspring2springweb.lesson8_controllers.controller.rest;

import com.example.javarushspring2springweb.lesson8_controllers.entity.Order;
import com.example.javarushspring2springweb.lesson8_controllers.entity.Product;
import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import com.example.javarushspring2springweb.lesson8_controllers.service.OrderService;
import com.example.javarushspring2springweb.lesson8_controllers.service.ProductService;
import com.example.javarushspring2springweb.lesson8_controllers.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class OrdersRestController {

    private final OrderService orderService;

    @GetMapping("api/orders")
    public ResponseEntity<List<Order>> orderList() {
        List<Order> allOrders = orderService.getAll();
        return ResponseEntity.ok(allOrders);
    }

    @PostMapping("api/orders")
    public ResponseEntity<?> addOrder(
            @RequestParam("userId") String userId,
            @RequestParam("address") String address,
            @RequestParam("productIds") String[] productIds
    ) {
        Optional<Order> createdOrderOptional = orderService.create(
                userId,
                address,
                productIds
        );

        if (createdOrderOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderOptional.get());
        }

        return ResponseEntity.badRequest().body("Could not create order due to invalid input parameters");

    }



}
