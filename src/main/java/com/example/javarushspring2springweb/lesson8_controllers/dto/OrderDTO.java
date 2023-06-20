package com.example.javarushspring2springweb.lesson8_controllers.dto;

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
    private UserDTO userDTO;
    private String address;
    private List<ProductDTO> orderList;
}

