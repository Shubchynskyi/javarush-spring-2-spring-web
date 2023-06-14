package com.example.javarushspring2springweb.lesson8_jpa_commands.repository;


import com.example.javarushspring2springweb.lesson8_jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepo extends JpaRepository<Product, Long> {
}
