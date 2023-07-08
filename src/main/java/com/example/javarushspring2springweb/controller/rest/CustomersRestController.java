package com.example.javarushspring2springweb.controller.rest;

import com.example.javarushspring2springweb.entity.Customer;
import com.example.javarushspring2springweb.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;


@RestController
@AllArgsConstructor
public class CustomersRestController {

    private final CustomerService customerService;

    @GetMapping("/api/customers")
    public ResponseEntity<List<Customer>> customersList() {
        List<Customer> allCustomers = customerService.getAll();
        return ResponseEntity.ok(allCustomers);
    }

    @PostMapping("/api/customers")
    public ResponseEntity<?> createCustomer(
            @RequestParam("name") String name,
            @RequestParam("password") String password
    ) {
        Optional<Customer> createdCustomerOptional = customerService.create(name, password);

        if (createdCustomerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerOptional.get());
        }

        return ResponseEntity.badRequest().body("Could not create customer due to invalid input parameters");

    }

}

