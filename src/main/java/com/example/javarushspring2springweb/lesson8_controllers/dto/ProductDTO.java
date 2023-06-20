package com.example.javarushspring2springweb.lesson8_controllers.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductDTO {
    private Long id;
    private String title;
    private String description;
}

