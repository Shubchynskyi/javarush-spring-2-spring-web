package com.example.javarushspring2springweb.lessons1_7.controller.comands;

import com.example.javarushspring2springweb.lessons1_7.entity.User;
import com.example.javarushspring2springweb.lessons1_7.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller("/user")
@RequiredArgsConstructor
public class UserCommand implements Command {

    private final UserService userService;

    @Override
    public String doGet(HttpServletRequest request) {
        String userId = request.getParameter("id");
        User user = userService.get(Long.valueOf(userId)).orElse(null);
        request.setAttribute("user", user);

        return "/user";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }


}

