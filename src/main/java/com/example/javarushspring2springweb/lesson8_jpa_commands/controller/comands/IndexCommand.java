package com.example.javarushspring2springweb.lesson8_jpa_commands.controller.comands;

import com.example.javarushspring2springweb.lesson8_jpa.controller.comands.Command;
import jakarta.servlet.RequestDispatcher;
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
