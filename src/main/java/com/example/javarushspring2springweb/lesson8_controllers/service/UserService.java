package com.example.javarushspring2springweb.lesson8_controllers.service;


import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import com.example.javarushspring2springweb.lesson8_controllers.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    @Transactional
    public Optional<User> get(Long id) {
        return userRepo.findById(id);
    }

    @Transactional
    public List<User> getAll() {
        Sort sort = Sort.sort(User.class).by(User::getId); // сортировка по id
        return userRepo.findAll(sort);
    }

    @Transactional
    public Optional<User> create (String name, String password) {
        User build = User.builder()
                .name(name)
                .password(password)
                .build();
        return Optional.of(userRepo.saveAndFlush(build));
    }

    @Transactional
    public void delete(User user) {
        userRepo.delete(user);
    }

    @Transactional
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }


}
