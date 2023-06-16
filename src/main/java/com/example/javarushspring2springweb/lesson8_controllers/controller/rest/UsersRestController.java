package com.example.javarushspring2springweb.lesson8_controllers.controller.rest;

import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import com.example.javarushspring2springweb.lesson8_controllers.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;


@RestController
@AllArgsConstructor
public class UsersRestController {

    private final UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> usersList() {
        List<User> allUsers = userService.getAll();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping("/api/users")
    public ResponseEntity<?> createUser(
            @RequestParam("name") String name,
            @RequestParam("password") String password
    ) {
        Optional<User> createdUserOptional = userService.create(name, password);

        if (createdUserOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUserOptional.get());
        }

        return ResponseEntity.badRequest().body("Could not create user due to invalid input parameters");

    }

}

