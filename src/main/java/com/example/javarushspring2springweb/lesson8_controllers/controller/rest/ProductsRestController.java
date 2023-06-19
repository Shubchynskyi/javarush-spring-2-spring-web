package com.example.javarushspring2springweb.lesson8_controllers.controller.rest;

import com.example.javarushspring2springweb.lesson8_controllers.entity.Product;
import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import com.example.javarushspring2springweb.lesson8_controllers.service.ProductService;
import com.example.javarushspring2springweb.lesson8_controllers.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
public class ProductsRestController {

    private final ProductService productService;

    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> usersList() {
        List<Product> allProducts = productService.getAll();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping("/api/products")
    public ResponseEntity<?> createUser(
            @RequestParam("title") String title,
            @RequestParam("description") String description
    ) {
        Optional<Product> createdProductOptional = productService.create(title, description);

        if (createdProductOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProductOptional.get());
        }

        return ResponseEntity.badRequest().body("Could not create product due to invalid input parameters");

    }

}
