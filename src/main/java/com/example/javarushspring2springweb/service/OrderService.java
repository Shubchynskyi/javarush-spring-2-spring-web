package com.example.javarushspring2springweb.service;

import com.example.javarushspring2springweb.entity.User;
import com.example.javarushspring2springweb.repository.AbstractRepo;
import com.example.javarushspring2springweb.entity.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderService {
    private final AbstractRepo<Order> orderRepo;

    public Optional<Order> get(Long id) {
        return Optional.of(orderRepo.getById(id));
    }
    public List<Order> getAll() {
        return orderRepo.getAll();
    }
}
