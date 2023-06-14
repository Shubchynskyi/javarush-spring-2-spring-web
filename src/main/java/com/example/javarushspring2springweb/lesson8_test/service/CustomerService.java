package com.example.javarushspring2springweb.lesson8_test.service;

import com.example.javarushspring2springweb.lesson8_test.entity.Customer;
import com.example.javarushspring2springweb.lesson8_test.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;

    public Optional<Customer> get(Long id) {
        return customerRepo.findById(id);
    }

    public void update(Customer customer){
        customerRepo.save(customer);
    }
}
