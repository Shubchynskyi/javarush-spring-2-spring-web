package com.example.javarushspring2springweb.repository;

import com.example.javarushspring2springweb.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepo extends JpaRepository<Order, Long> {
}
