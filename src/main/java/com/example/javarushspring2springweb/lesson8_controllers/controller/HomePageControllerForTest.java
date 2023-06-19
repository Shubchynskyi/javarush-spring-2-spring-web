package com.example.javarushspring2springweb.lesson8_controllers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageControllerForTest {

    @RequestMapping(method = RequestMethod.GET, value = "test")
    public String getHomePage() {
        return "redirect:/users";
    }
}