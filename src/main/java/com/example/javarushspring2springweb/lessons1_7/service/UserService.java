package com.example.javarushspring2springweb.lessons1_7.service;

//import com.example.javarushspring2springweb.lessons1_7.config.bpp.Logger;
//import com.example.javarushspring2springweb.lessons1_7.config.bpp.Tx;
//import com.example.javarushspring2springweb.lessons1_7.config.aspect.LoggerAspect;
//import com.example.javarushspring2springweb.lessons1_7.config.aspect.Tx;
//import com.example.javarushspring2springweb.lessons1_7.config.bpp.Logger;
//import com.example.javarushspring2springweb.lessons1_7.config.bpp.Tx;
import com.example.javarushspring2springweb.lessons1_7.config.aspect.LoggerAspect;
import com.example.javarushspring2springweb.lessons1_7.config.aspect.Tx;
import com.example.javarushspring2springweb.lessons1_7.entity.User;
import com.example.javarushspring2springweb.lessons1_7.repository.UserRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
//@AspectLogger
@NoArgsConstructor
//@AllArgsConstructor
public class UserService {


    private  UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @LoggerAspect
    public Optional<User> get(Long id) {
        return Optional.of(userRepo.getById(id));
    }

    public List<User> getAll() {
        return userRepo.getAll();
    }

//    @Logger
//    @Tx
//    @LoggerAspect
    @Tx
    public void create(String name, String password) {
        User build = User.builder()
                .name(name)
                .password(password)
                .build();
        userRepo.create(build);
    }


}
