package com.example.javarushspring2springweb.controller;

import com.example.javarushspring2springweb.config.JavaConfigApplication;
import com.example.javarushspring2springweb.controller.comands.CommandResolver;
import com.example.javarushspring2springweb.view.ViewResolver;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"", "/user", "/users", "/order", "/orders"}, loadOnStartup = 0)
public class FrontServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        JavaConfigApplication.init();
        super.init(config);
    }

    private final CommandResolver commandResolver = JavaConfigApplication.getContext().getBean(CommandResolver.class);
    private final ViewResolver viewResolver = JavaConfigApplication.getContext().getBean(ViewResolver.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resolved = commandResolver.resolveCommand(req);
        viewResolver.goToView(req, resp, "forward", resolved);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
