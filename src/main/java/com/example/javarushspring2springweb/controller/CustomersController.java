package com.example.javarushspring2springweb.controller;

import com.example.javarushspring2springweb.dto.CustomerDTO;
import com.example.javarushspring2springweb.mapper.CustomerMapper;
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
public class CustomersController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping("customers")
    public String customersList(Model model) {
        List<CustomerDTO> customerDTOS = customerService.getAll().stream()
                .map(customerMapper::customerToCustomerDTOWithoutPassword)
                .collect(Collectors.toList());
        model.addAttribute("customers", customerDTOS);
        return "customers";
    }

    @PostMapping("customers")
    public String createCustomer(
            @RequestParam("name") String name,
            @RequestParam("password") String password
    ) {
        customerService.create(name, password);
        return "redirect:customers";
    }
}
