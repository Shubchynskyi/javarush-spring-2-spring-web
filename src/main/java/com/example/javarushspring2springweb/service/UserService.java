package com.example.javarushspring2springweb.service;

import com.example.javarushspring2springweb.config.Logger;
import com.example.javarushspring2springweb.entity.User;
import com.example.javarushspring2springweb.repository.AbstractRepo;
import com.example.javarushspring2springweb.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserService() {

    }


    public Optional<User> get(Long id) {
        return Optional.of(userRepo.getById(id));
    }

    public List<User> getAll() {
        return userRepo.getAll();
    }

    @Logger
    public void create(String name, String password) {
        User build = User.builder()
                .name(name)
                .password(password)
                .build();
        userRepo.create(build);
    }


}
