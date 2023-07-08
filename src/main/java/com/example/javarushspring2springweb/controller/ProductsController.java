package com.example.javarushspring2springweb.controller;

import com.example.javarushspring2springweb.dto.ProductDTO;
import com.example.javarushspring2springweb.mapper.ProductMapper;
import com.example.javarushspring2springweb.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ProductsController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("products")
    public String showProducts(Model model) {
        List<ProductDTO> products = productService.getAll().stream()
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("products")
    public String addProduct(
            @RequestParam("title") String title,
            @RequestParam("description") String description
    ) {
        productService.create(title, description);
        return "redirect:products";
    }
}
