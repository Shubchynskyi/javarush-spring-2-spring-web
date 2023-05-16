package com.example;

import com.example.javarushspring2springweb.config.JavaConfigApplication;
import com.example.javarushspring2springweb.config.Logger;
import com.example.javarushspring2springweb.service.UserService;

public class Main {


    public static void main(String[] args) {

        JavaConfigApplication.init();
        UserService userService = JavaConfigApplication.getContext().getBean(UserService.class);

        userService.create("username123", "userPass123");
        System.out.println(userService.get(2L));
    }
}
