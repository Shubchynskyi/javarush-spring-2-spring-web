package com.example.javarushspring2springweb.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderDTO {
    private Long id;
    private CustomerDTO customerDTO;
    private String address;
    private List<ProductDTO> orderList;
}

