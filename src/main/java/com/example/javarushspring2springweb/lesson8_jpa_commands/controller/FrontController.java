package com.example.javarushspring2springweb.lesson8_jpa_commands.controller;

import com.example.javarushspring2springweb.lesson8_jpa.controller.comands.CommandResolver;
import com.example.javarushspring2springweb.lesson8_jpa.view.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class FrontController {

    private final CommandResolver commandResolver;
    private final ViewResolver viewResolver;

    public FrontController(CommandResolver commandResolver, ViewResolver viewResolver) {
        this.commandResolver = commandResolver;
        this.viewResolver = viewResolver;
    }

    @RequestMapping(value = {"", "/user", "/users", "/order", "/orders", "/product", "/products"}, method = RequestMethod.GET)
    public void handleGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String resolved = commandResolver.resolveCommand(req);
        viewResolver.goToView(req, resp, "forward", resolved);
    }

    @RequestMapping(value = {"", "/user", "/users", "/order", "/orders", "/product", "/products"}, method = RequestMethod.POST)
    public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String resolved = commandResolver.resolveCommand(req, resp);
        viewResolver.goToView(req, resp, "redirect", resolved);
    }
}