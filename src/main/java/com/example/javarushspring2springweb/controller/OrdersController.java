package com.example.javarushspring2springweb.controller;

import com.example.javarushspring2springweb.dto.OrderDTO;
import com.example.javarushspring2springweb.dto.ProductDTO;
import com.example.javarushspring2springweb.dto.CustomerDTO;
import com.example.javarushspring2springweb.mapper.OrderMapper;
import com.example.javarushspring2springweb.mapper.ProductMapper;
import com.example.javarushspring2springweb.mapper.CustomerMapper;
import com.example.javarushspring2springweb.service.OrderService;
import com.example.javarushspring2springweb.service.ProductService;
import com.example.javarushspring2springweb.service.CustomerService;
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
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;
    private final ProductMapper productMapper;

    @GetMapping("orders")
    public String showOrders(
            Model model
    ) {
        List<OrderDTO> orders = orderService.getAll().stream()
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
        List<CustomerDTO> customerDTOS = customerService.getAll().stream()
                .map(customerMapper::customerToCustomerDTOWithoutPassword)
                .collect(Collectors.toList());
        List<ProductDTO> products = productService.getAll().stream()
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
        model.addAttribute("orders", orders);
        model.addAttribute("customers", customerDTOS);
        model.addAttribute("products", products);
        return "orders";
    }

    @PostMapping("orders")
    public String addOrder(
            @RequestParam("customerId") String customerId,
            @RequestParam("address") String address,
            @RequestParam("productIds") String[] productIds
    ) {
        orderService.create(
                customerId,
                address,
                productIds
        );
        return "redirect:orders";
    }
}
