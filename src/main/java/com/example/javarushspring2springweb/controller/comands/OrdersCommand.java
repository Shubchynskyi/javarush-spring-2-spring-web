package com.example.javarushspring2springweb.controller.comands;

import com.example.javarushspring2springweb.entity.Order;
import com.example.javarushspring2springweb.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller("/orders")
@RequiredArgsConstructor
public class OrdersCommand implements Command {

    private final OrderService orderService;

    @Override
    public String doGet(HttpServletRequest request) {
        Collection<Order> orders = orderService.getAll();
        request.setAttribute("orders", orders);
        return "/orders";
    }
}
