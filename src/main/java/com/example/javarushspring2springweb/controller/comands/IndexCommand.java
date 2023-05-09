package com.example.javarushspring2springweb.controller.comands;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller("")
public class IndexCommand implements Command{


    @Override
    public String doGet(HttpServletRequest request) {
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/index.jsp");

        return "";
    }
}
