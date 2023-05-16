package com.example.javarushspring2springweb.controller.comands;

import com.example.javarushspring2springweb.entity.Order;
import com.example.javarushspring2springweb.entity.Product;
import com.example.javarushspring2springweb.entity.User;
import com.example.javarushspring2springweb.service.OrderService;
import com.example.javarushspring2springweb.service.ProductService;
import com.example.javarushspring2springweb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("/orders")
public class OrdersCommand implements Command {

    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;
    private final String firstProperty;
    private final String secondProperty;


    public OrdersCommand(
            @Value("${my.property}") String firstProperty,
            @Value("${another.property}") String secondProperty,
            OrderService orderService,
            UserService userService,
            ProductService productService
    ) {
        System.out.println(firstProperty);
        System.out.println(secondProperty);
        this.firstProperty = firstProperty;
        this.secondProperty = secondProperty;
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public String doGet(HttpServletRequest request) {
        System.out.println(firstProperty);
        System.out.println(secondProperty);
        List<Order> orders = orderService.getAll();
        List<User> users = userService.getAll();
        List<Product> products = productService.getAll();
        request.setAttribute("orders", orders);
        request.setAttribute("users", users);
        request.setAttribute("products", products);
        return "/orders";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        orderService.create(
                request.getParameter("userId"),
                request.getParameter("address"),
                request.getParameterValues("productIds")
        );
        return "/orders";
    }
}
