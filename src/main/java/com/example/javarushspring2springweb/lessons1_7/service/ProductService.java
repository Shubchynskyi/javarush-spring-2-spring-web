package com.example.javarushspring2springweb.lessons1_7.service;

import com.example.javarushspring2springweb.lessons1_7.entity.Product;
import com.example.javarushspring2springweb.lessons1_7.repository.AbstractRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {
    private final AbstractRepo<Product> productRepo;

    public Optional<Product> get(Long id) {
        return Optional.of(productRepo.getById(id));
    }

    public List<Product> getAll() {
        return productRepo.getAll();
    }

    public void create(String title, String description) {
        Product build = Product.builder()
                .title(title)
                .description(description)
                .build();
        productRepo.create(build);
    }


}
