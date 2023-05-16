package com.example.javarushspring2springweb.controller.comands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;

@Controller("")
public class IndexCommand implements Command {


    @Override
    public String doGet(HttpServletRequest request) {
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/index.jsp");

        return "";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }


}
