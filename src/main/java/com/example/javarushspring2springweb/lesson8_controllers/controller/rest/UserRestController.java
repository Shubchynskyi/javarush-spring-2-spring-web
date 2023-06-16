package com.example.javarushspring2springweb.lesson8_controllers.controller.rest;

import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import com.example.javarushspring2springweb.lesson8_controllers.exeption.ResourceNotFoundException;
import com.example.javarushspring2springweb.lesson8_controllers.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping("api/user")
    public ResponseEntity<User> showUser(@RequestParam("id") Long id){
        User user = userService.get(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id " + id)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
