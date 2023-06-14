package com.example.javarushspring2springweb.lessons1_7.controller;

import com.example.javarushspring2springweb.lessons1_7.config.JavaConfigApplication;
import com.example.javarushspring2springweb.lessons1_7.controller.comands.CommandResolver;
import com.example.javarushspring2springweb.lessons1_7.view.ViewResolver;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"", "/user", "/users", "/order", "/orders", "/product", "/products"}, loadOnStartup = 0)
public class FrontServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        JavaConfigApplication.init();
        super.init(config);
    }

    private final CommandResolver commandResolver = JavaConfigApplication.getBean(CommandResolver.class);
    private final ViewResolver viewResolver = JavaConfigApplication.getBean(ViewResolver.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resolved = commandResolver.resolveCommand(req);
        viewResolver.goToView(req, resp, "forward", resolved);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resolved = commandResolver.resolveCommand(req, resp);
        viewResolver.goToView(req, resp, "redirect", resolved);
    }
}
