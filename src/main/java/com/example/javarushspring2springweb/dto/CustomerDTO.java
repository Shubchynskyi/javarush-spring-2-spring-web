package com.example.javarushspring2springweb.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerDTO {
    private Long id;
    private String name;
    private String password;
}
