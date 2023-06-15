package com.example.javarushspring2springweb.lesson8_controllers.controller;

import com.example.javarushspring2springweb.lesson8_controllers.entity.Order;
import com.example.javarushspring2springweb.lesson8_controllers.entity.Product;
import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
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

@Controller
@AllArgsConstructor
public class OrdersController {

    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("orders")
    public String showOrders(
            Model model
    ) {
        List<Order> orders = orderService.getAll();
        List<User> users = userService.getAll();
        List<Product> products = productService.getAll();
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
