package com.example.javarushspring2springweb.controller;

import com.example.javarushspring2springweb.dto.ProductDTO;
import com.example.javarushspring2springweb.entity.Product;
import com.example.javarushspring2springweb.exeption.ResourceNotFoundException;
import com.example.javarushspring2springweb.mapper.ProductMapper;
import com.example.javarushspring2springweb.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("product")
    public String showProduct(
            @RequestParam("id") String id,
            Model model
    ) {
        Product product = productService.get(Long.valueOf(id)).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id " + id)
        );

        ProductDTO productDTO = productMapper.productToProductDTO(product);

        model.addAttribute("product", productDTO);

        return "product";
    }
}

