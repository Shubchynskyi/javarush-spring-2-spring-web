package com.example.javarushspring2springweb.lessons1_7.controller.comands;

import com.example.javarushspring2springweb.lessons1_7.entity.Product;
import com.example.javarushspring2springweb.lessons1_7.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        productService.create(title,description);
        return "/products";
    }
}
