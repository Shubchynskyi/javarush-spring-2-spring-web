package com.example.javarushspring2springweb.controller.rest;

import com.example.javarushspring2springweb.entity.Order;
import com.example.javarushspring2springweb.exeption.ResourceNotFoundException;
import com.example.javarushspring2springweb.service.OrderService;
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
    public ResponseEntity<Order> showOrder(@RequestParam("id") Long id){
        Order order = orderService.get(id).orElseThrow(
                () -> new ResourceNotFoundException("Order not found with id " + id)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}
