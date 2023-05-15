package com.example.javarushspring2springweb.service;

import com.example.javarushspring2springweb.entity.User;
import com.example.javarushspring2springweb.repository.AbstractRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private final AbstractRepo<User> userRepo;

    public Optional<User> get(Long id) {
        return Optional.of(userRepo.getById(id));
    }

    public List<User> getAll() {
        return userRepo.getAll();
    }

    public void create(String name, String password) {
        User build = User.builder()
                .name(name)
                .password(password)
                .build();
        userRepo.create(build);
    }


}
