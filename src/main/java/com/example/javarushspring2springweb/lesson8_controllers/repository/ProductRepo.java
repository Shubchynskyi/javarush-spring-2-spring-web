package com.example.javarushspring2springweb.lesson8_controllers.repository;


import com.example.javarushspring2springweb.lesson8_controllers.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepo extends JpaRepository<Product, Long> {
}
