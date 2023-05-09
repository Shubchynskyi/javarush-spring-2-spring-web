package com.example.javarushspring2springweb.controller.comands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Command {
    String doGet(HttpServletRequest request);
}
