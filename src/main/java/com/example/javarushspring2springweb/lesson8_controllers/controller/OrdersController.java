package com.example.javarushspring2springweb.lesson8_controllers.controller;

import com.example.javarushspring2springweb.lesson8_controllers.dto.OrderDTO;
import com.example.javarushspring2springweb.lesson8_controllers.dto.ProductDTO;
import com.example.javarushspring2springweb.lesson8_controllers.dto.UserDTO;
import com.example.javarushspring2springweb.lesson8_controllers.mapper.OrderMapper;
import com.example.javarushspring2springweb.lesson8_controllers.mapper.ProductMapper;
import com.example.javarushspring2springweb.lesson8_controllers.mapper.UserMapper;
import com.example.javarushspring2springweb.lesson8_controllers.service.OrderService;
import com.example.javarushspring2springweb.lesson8_controllers.service.ProductService;
import com.example.javarushspring2springweb.lesson8_controllers.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class OrdersController {

    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;
    private final OrderMapper orderMapper;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;

    @GetMapping("orders")
    public String showOrders(
            Model model
    ) {
        List<OrderDTO> orders = orderService.getAll().stream()
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
        List<UserDTO> users = userService.getAll().stream()
                .map(userMapper::userToUserDTOWithoutPassword)
                .collect(Collectors.toList());
        List<ProductDTO> products = productService.getAll().stream()
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
        model.addAttribute("orders", orders);
        model.addAttribute("users", users);
        model.addAttribute("products", products);
        return "orders";
    }

    @PostMapping("orders")
    public String addOrder(
            @RequestParam("userId") String userId,
            @RequestParam("address") String address,
            @RequestParam("productIds") String[] productIds
    ) {
        orderService.create(
                userId,
                address,
                productIds
        );
        return "redirect:orders";
    }
}
