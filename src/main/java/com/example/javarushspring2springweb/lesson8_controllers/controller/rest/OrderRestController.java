package com.example.javarushspring2springweb.lesson8_controllers.controller.rest;

import com.example.javarushspring2springweb.lesson8_controllers.entity.Order;
import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import com.example.javarushspring2springweb.lesson8_controllers.exeption.ResourceNotFoundException;
import com.example.javarushspring2springweb.lesson8_controllers.service.OrderService;
import com.example.javarushspring2springweb.lesson8_controllers.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping("api/order")
    public ResponseEntity<Order> showUser(@RequestParam("id") Long id){
        Order order = orderService.get(id).orElseThrow(
                () -> new ResourceNotFoundException("Order not found with id " + id)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}
