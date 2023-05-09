package com.example.javarushspring2springweb.controller.comands;

import com.example.javarushspring2springweb.entity.User;
import com.example.javarushspring2springweb.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Collection;

@Controller(value = "/users")
@RequiredArgsConstructor
public class UsersCommand implements Command {

    private final UserService userService;

    @Override
    public String doGet(HttpServletRequest request) {
        Collection<User> users = userService.getAll();
        request.setAttribute("users", users);
        return "/users";
    }
}
