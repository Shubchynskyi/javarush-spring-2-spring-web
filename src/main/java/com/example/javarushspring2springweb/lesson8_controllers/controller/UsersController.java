package com.example.javarushspring2springweb.lesson8_controllers.controller;

import com.example.javarushspring2springweb.lesson8_controllers.dto.UserDTO;
import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import com.example.javarushspring2springweb.lesson8_controllers.mapper.UserMapper;
import com.example.javarushspring2springweb.lesson8_controllers.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class UsersController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("users")
    public String usersList(Model model) {
        List<UserDTO> allUsers = userService.getAll().stream()
                .map(userMapper::userToUserDTOWithoutPassword)
                .collect(Collectors.toList());
        model.addAttribute("users", allUsers);
        return "users";
    }

    @PostMapping("users")
    public String createUser(
            @RequestParam("name") String name,
            @RequestParam("password") String password
    ) {
        userService.create(name, password);
        return "redirect:users";
    }
}
