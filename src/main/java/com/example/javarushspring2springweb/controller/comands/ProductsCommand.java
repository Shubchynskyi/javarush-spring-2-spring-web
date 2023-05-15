package com.example.javarushspring2springweb.controller.comands;

import com.example.javarushspring2springweb.entity.Product;
import com.example.javarushspring2springweb.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller(value = "/products")
@RequiredArgsConstructor
public class ProductsCommand implements Command {

    private final ProductService productService;

    @Override
    public String doGet(HttpServletRequest request) {
        List<Product> products = productService.getAll();
        request.setAttribute("products", products);
        return "/products";
    }
}
