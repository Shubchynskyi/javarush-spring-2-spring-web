package com.example.javarushspring2springweb.controller.comands;

import com.example.javarushspring2springweb.entity.Order;
import com.example.javarushspring2springweb.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller("/orders")
public class OrdersCommand implements Command {

    private final OrderService orderService;
    private final String firstProperty;
    private final String secondProperty;


    public OrdersCommand(
            @Value("${my.property}") String firstProperty,
            @Value("${another.property}") String secondProperty,
            OrderService orderService) {
        System.out.println(firstProperty);
        System.out.println(secondProperty);
        this.firstProperty = firstProperty;
        this.secondProperty = secondProperty;
        this.orderService = orderService;
    }

    @Override
    public String doGet(HttpServletRequest request) {
        System.out.println(firstProperty);
        System.out.println(secondProperty);
        Collection<Order> orders = orderService.getAll();
        request.setAttribute("orders", orders);
        return "/orders";
    }
}
