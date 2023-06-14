package com.example.javarushspring2springweb.lesson8_controllers.view;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class ViewResolver {

    public void goToView(HttpServletRequest req, HttpServletResponse resp, String action, String target) throws ServletException, IOException {
        if (action.equals("forward")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(views.get(target));
            dispatcher.forward(req, resp);
        } else if (action.equals("redirect")) {
            resp.sendRedirect(target);
        }

    }

    @Autowired
    public void setViews(Map<String, String> views) {
        this.views = views;
    }

    private Map<String, String> views;

}
