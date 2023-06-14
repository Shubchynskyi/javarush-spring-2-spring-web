package com.example.javarushspring2springweb.lesson8_controllers.controller.comands;

import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import com.example.javarushspring2springweb.lesson8_controllers.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller(value = "/users")
@RequiredArgsConstructor
public class UsersCommand implements Command {

    private final UserService userService;

    @Override
    public String doGet(HttpServletRequest request) {
        List<User> users = userService.getAll();
        request.setAttribute("users", users);
        return "/users";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        userService.create(name, password);
        return "/users";
    }
}
