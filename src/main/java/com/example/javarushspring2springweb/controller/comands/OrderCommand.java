package com.example.javarushspring2springweb.controller.comands;

import com.example.javarushspring2springweb.entity.Order;
import com.example.javarushspring2springweb.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller("/order")
@RequiredArgsConstructor
public class OrderCommand implements Command {

    private final OrderService orderService;

    @Override
    public String doGet(HttpServletRequest request) {
        String orderId = request.getParameter("id");
        Order order = orderService.get(Long.valueOf(orderId)).orElse(null);
        request.setAttribute("order", order);

        return "/order";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }


}

