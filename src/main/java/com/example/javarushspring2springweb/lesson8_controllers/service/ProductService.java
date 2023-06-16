package com.example.javarushspring2springweb.lesson8_controllers.service;

import com.example.javarushspring2springweb.lesson8_controllers.entity.Product;
import com.example.javarushspring2springweb.lesson8_controllers.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepo productRepo;

    public Optional<Product> get(Long id) {
        return Optional.of(productRepo.getReferenceById(id));
    }

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Optional<Product> create(String title, String description) {
        Product build = Product.builder()
                .title(title)
                .description(description)
                .build();
        return Optional.of(productRepo.save(build));
    }
}
