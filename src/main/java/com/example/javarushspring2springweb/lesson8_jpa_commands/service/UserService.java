package com.example.javarushspring2springweb.lesson8_jpa_commands.service;


import com.example.javarushspring2springweb.lesson8_jpa.entity.User;
import com.example.javarushspring2springweb.lesson8_jpa.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void create(String name, String password) {
        User build = User.builder()
                .name(name)
                .password(password)
                .build();
        userRepo.save(build);
    }


}
