package com.example.javarushspring2springweb.lesson8_controllers.repository;

import com.example.javarushspring2springweb.lesson8_controllers.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepo extends JpaRepository<Order, Long> {
}
