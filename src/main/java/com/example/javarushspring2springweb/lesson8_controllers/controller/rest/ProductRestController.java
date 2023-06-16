package com.example.javarushspring2springweb.lesson8_controllers.controller.rest;

import com.example.javarushspring2springweb.lesson8_controllers.entity.Product;
import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import com.example.javarushspring2springweb.lesson8_controllers.exeption.ResourceNotFoundException;
import com.example.javarushspring2springweb.lesson8_controllers.service.ProductService;
import com.example.javarushspring2springweb.lesson8_controllers.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("api/product")
    public ResponseEntity<Product> showUser(@RequestParam("id") Long id){
        Product product = productService.get(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id " + id)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

}
