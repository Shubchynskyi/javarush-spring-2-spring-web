package com.example.javarushspring2springweb.lesson8_jpa_commands.controller.comands;

import com.example.javarushspring2springweb.lesson8_jpa.controller.comands.Command;
import com.example.javarushspring2springweb.lesson8_jpa.entity.Product;
import com.example.javarushspring2springweb.lesson8_jpa.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller("/product")
@RequiredArgsConstructor
public class ProductCommand implements Command {

    private final ProductService productService;

    @Override
    public String doGet(HttpServletRequest request) {
        String productId = request.getParameter("id");
        Product product = productService.get(Long.valueOf(productId)).orElse(null);
        request.setAttribute("product", product);

        return "/product";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}

