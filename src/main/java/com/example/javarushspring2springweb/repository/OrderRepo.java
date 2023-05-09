package com.example.javarushspring2springweb.repository;

import com.example.javarushspring2springweb.entity.Order;
import com.example.javarushspring2springweb.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Repository
public class OrderRepo implements AbstractRepo<Order> {

    private final Map<Long, Order> orderMap = Map.of(
            15L, new Order(15L, new User(1L,"userInOrder1","qwertyO1"),
                    "some address",
                    List.of(
                            "товар 1",
                            "товар 2",
                            "товар 3",
                            "товар 4"
                    )),
            16L, new Order(16L, new User(2L,"userInOrder2","qwertyO2"),
                    "some address2",
                    List.of(
                            "товар 5",
                            "товар 6",
                            "товар 7",
                            "товар 8"
                    ))
    );

    public Order getById(Long id) {
        return orderMap.get(id);
    }

    @Override
    public List<Order> getAll() {
        return orderMap.values().stream().toList();
    }
}
