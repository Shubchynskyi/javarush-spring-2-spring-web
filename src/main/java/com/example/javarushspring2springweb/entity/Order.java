package com.example.javarushspring2springweb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Order {
    private Long id;
    private User user;
    private String address;
    private List<String> orderList;
}
