package com.example.javarushspring2springweb.lesson8_controllers.service;


import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import com.example.javarushspring2springweb.lesson8_controllers.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public Optional<User> get(Long id) {
        return Optional.of(userRepo.getReferenceById(id));
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public Optional<User> create (String name, String password) {
        User build = User.builder()
                .name(name)
                .password(password)
                .build();
        return Optional.of(userRepo.save(build));
    }


}
