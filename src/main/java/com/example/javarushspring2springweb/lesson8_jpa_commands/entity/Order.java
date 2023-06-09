package com.example.javarushspring2springweb.lesson8_jpa_commands.entity;


import com.example.javarushspring2springweb.lesson8_jpa.entity.Product;
import com.example.javarushspring2springweb.lesson8_jpa.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    @ToString.Exclude
    private User user;
    private String address;

    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @ToString.Exclude
    private List<Product> orderList;

}
