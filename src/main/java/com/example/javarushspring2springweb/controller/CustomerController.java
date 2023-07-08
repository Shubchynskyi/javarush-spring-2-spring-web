package com.example.javarushspring2springweb.controller;

import com.example.javarushspring2springweb.dto.CustomerDTO;
import com.example.javarushspring2springweb.entity.Customer;
import com.example.javarushspring2springweb.exeption.ResourceNotFoundException;
import com.example.javarushspring2springweb.mapper.CustomerMapper;
import com.example.javarushspring2springweb.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping("customer")
    public String showCustomer(@RequestParam("id") Long id, Model model) {
        Customer customer = customerService.get(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer not found with id " + id)
        );

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTOWithoutPassword(customer);

        model.addAttribute("customer", customerDTO);
        return "customer";
    }

}
