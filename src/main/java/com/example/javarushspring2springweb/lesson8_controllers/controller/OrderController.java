package com.example.javarushspring2springweb.lesson8_controllers.controller;

import com.example.javarushspring2springweb.lesson8_controllers.dto.OrderDTO;
import com.example.javarushspring2springweb.lesson8_controllers.entity.Order;
import com.example.javarushspring2springweb.lesson8_controllers.exeption.ResourceNotFoundException;
import com.example.javarushspring2springweb.lesson8_controllers.mapper.OrderMapper;
import com.example.javarushspring2springweb.lesson8_controllers.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class OrderController{

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("order")
    public String showOrder(
            @RequestParam("id") String id,
            Model model
    ) {
        Order order = orderService.get(Long.valueOf(id)).orElseThrow(
                () -> new ResourceNotFoundException("Order not found with id " + id)
        );

        OrderDTO orderDTO = orderMapper.orderToOrderDTO(order);

        model.addAttribute("order", orderDTO);
        return "order";
    }

}

