package com.example.javarushspring2springweb.repository;


import com.example.javarushspring2springweb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepo extends JpaRepository<Product, Long> {
}
