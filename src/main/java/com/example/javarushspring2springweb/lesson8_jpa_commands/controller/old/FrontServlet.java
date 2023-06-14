package com.example.javarushspring2springweb.lesson8_jpa_commands.controller.old;

import com.example.javarushspring2springweb.lesson8_jpa.controller.comands.CommandResolver;
import com.example.javarushspring2springweb.lesson8_jpa.view.ViewResolver;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@AllArgsConstructor
@WebServlet(urlPatterns = {"", "/user", "/users", "/order", "/orders", "/product", "/products"}, loadOnStartup = 0)
public class FrontServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    private CommandResolver commandResolver;
    private ViewResolver viewResolver;

    @Autowired
    private void setViewResolver(ViewResolver viewResolver) {
        this.viewResolver = viewResolver;
    }

    @Autowired
    private void setCommandResolver(CommandResolver commandResolver) {
        this.commandResolver = commandResolver;
    }


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
