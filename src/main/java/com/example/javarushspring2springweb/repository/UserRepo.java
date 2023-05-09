package com.example.javarushspring2springweb.repository;

import com.example.javarushspring2springweb.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Repository
public class UserRepo implements AbstractRepo<User> {

    private final Map<Long, User> userMap = Map.of(
            1L, new User(1L,"User1", "qwerty1"),
            2L, new User(2L,"User2", "qwerty2"),
            3L, new User(3L,"User3", "qwerty3"),
            4L, new User(4L,"User4", "qwerty4")
    );
    public User getById(Long id) {
        return userMap.get(id);
    }

    @Override
    public List<User> getAll() {
        return userMap.values().stream().toList();
    }
}
