package com.example.javarushspring2springweb.lesson8_controllers.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO {
    private Long id;
    private String name;
    private String password;
}
