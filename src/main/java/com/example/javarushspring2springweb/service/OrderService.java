package com.example.javarushspring2springweb.service;

import com.example.javarushspring2springweb.entity.Customer;
import com.example.javarushspring2springweb.entity.Product;
import com.example.javarushspring2springweb.entity.Order;
import com.example.javarushspring2springweb.repository.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final CustomerService customerService;
    private final ProductService productService;

    public Optional<Order> get(Long id) {
        return Optional.of(orderRepo.getReferenceById(id));
    }
    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    public Optional<Order> create(String customerId, String address, String[] productIds) {
        Optional<Customer> customerOptional = customerService.get(Long.parseLong(customerId));
        List<Product> productList = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> product = productService.get(Long.valueOf(productId));
            product.ifPresent(productList::add);
        }

        Customer customer = customerOptional.orElseThrow(() -> new RuntimeException("Customer not found"));

        Order build = Order.builder()
                .customer(customer)
                .address(address)
                .orderList(productList)
                .build();

        return Optional.of(orderRepo.save(build));
    }
}
