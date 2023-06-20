package com.example.javarushspring2springweb.lesson8_controllers.controller;

import com.example.javarushspring2springweb.lesson8_controllers.dto.UserDTO;
import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import com.example.javarushspring2springweb.lesson8_controllers.exeption.ResourceNotFoundException;
import com.example.javarushspring2springweb.lesson8_controllers.mapper.UserMapper;
import com.example.javarushspring2springweb.lesson8_controllers.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("user")
    public String showUser(@RequestParam("id") Long id, Model model) {
        User user = userService.get(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id " + id)
        );

        UserDTO userDTO = userMapper.userToUserDTOWithoutPassword(user);

        model.addAttribute("user", userDTO);
        return "user";
    }

}
