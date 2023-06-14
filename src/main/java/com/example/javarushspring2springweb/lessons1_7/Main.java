package com.example.javarushspring2springweb.lessons1_7;


import com.example.javarushspring2springweb.lessons1_7.config.JavaConfigApplication;
import com.example.javarushspring2springweb.lessons1_7.service.UserService;

public class Main {


    public static void main(String[] args) {
        System.err.println("TEST!!!!!!!!!!!!!!!!");
        JavaConfigApplication.init();
        UserService userService = JavaConfigApplication.getBean(UserService.class);

        userService.create("username546", "userPass123");
        System.out.println(userService.get(2L));
    }
}
