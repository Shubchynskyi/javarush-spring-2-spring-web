package com.example.javarushspring2springweb.repository;

import com.example.javarushspring2springweb.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
