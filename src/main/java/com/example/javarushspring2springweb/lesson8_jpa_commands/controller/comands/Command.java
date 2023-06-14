package com.example.javarushspring2springweb.lesson8_jpa_commands.controller.comands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
    String doGet(HttpServletRequest request);

    String doPost(HttpServletRequest request, HttpServletResponse response);
}
