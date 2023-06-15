package com.example.javarushspring2springweb.lesson8_controllers.controller;

import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import com.example.javarushspring2springweb.lesson8_controllers.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping("users")
    public String usersList(Model model) {
        List<User> allUsers = userService.getAll();
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
