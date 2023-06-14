package com.example.javarushspring2springweb.lessons1_7.controller.comands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
    String doGet(HttpServletRequest request);

    String doPost(HttpServletRequest request, HttpServletResponse response);
}
