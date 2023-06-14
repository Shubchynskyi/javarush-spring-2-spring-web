package com.example.javarushspring2springweb.lesson8_jpa_commands.entity;


import com.example.javarushspring2springweb.lesson8_jpa.entity.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ManyToMany(mappedBy = "orderList")
    @ToString.Exclude
    private final List<Order> orders = new ArrayList<>();

}
