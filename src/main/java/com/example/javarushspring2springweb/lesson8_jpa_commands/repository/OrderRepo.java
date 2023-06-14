package com.example.javarushspring2springweb.lesson8_jpa_commands.repository;

import com.example.javarushspring2springweb.lesson8_jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepo extends JpaRepository<Order, Long> {
}
