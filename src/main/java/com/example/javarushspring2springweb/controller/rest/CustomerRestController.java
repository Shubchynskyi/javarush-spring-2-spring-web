package com.example.javarushspring2springweb.controller.rest;

import com.example.javarushspring2springweb.entity.Customer;
import com.example.javarushspring2springweb.exeption.ResourceNotFoundException;
import com.example.javarushspring2springweb.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping("api/customer")
    public ResponseEntity<Customer> showCustomer(@RequestParam("id") Long id){
        Customer customer = customerService.get(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer not found with id " + id)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

}
