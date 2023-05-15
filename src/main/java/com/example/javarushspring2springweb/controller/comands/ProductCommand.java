package com.example.javarushspring2springweb.controller.comands;

import com.example.javarushspring2springweb.entity.Product;
import com.example.javarushspring2springweb.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
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
}
