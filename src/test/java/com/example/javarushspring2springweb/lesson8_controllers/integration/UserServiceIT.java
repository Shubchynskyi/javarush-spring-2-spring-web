package com.example.javarushspring2springweb.lesson8_controllers.integration;

import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import com.example.javarushspring2springweb.lesson8_controllers.service.UserService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MyTestContext
class UserServiceIT {

    @Autowired
    private EntityManager entityManager;

    private final UserService userService;
    public static final long USER_ID = 1L;

    private User inUser;
    private User expextedUser;

    UserServiceIT(UserService userService) {
        this.userService = userService;
    }

    @BeforeEach
    void setUp() {
        inUser = User.builder()
                .name("test_login")
                .password("test_password")
                .build();
        expextedUser = User.builder()
                .id(USER_ID)
                .name("Admin")
                .password("123")
                .build();
    }

    @Test
    @Transactional
    void get() {
        User user = userService.get(USER_ID).orElseThrow();
        assertEquals(expextedUser.getId(), user.getId());
    }

    @Test
    void getAll() {
        List<User> allUsers = userService.getAll();
        assertTrue(allUsers.size() > 1, "List should have more than one user");
    }

    @Test
    @Transactional
    @Rollback // это по умолчанию для тестов которые @Transactional
    void create() {
        User actualUser = userService.create(inUser.getName(), inUser.getPassword()).orElseThrow();
        assertTrue(actualUser.getId() > 0);
        assertEquals(inUser.getName(), actualUser.getName());
        assertEquals(inUser.getPassword(), actualUser.getPassword());
    }

    @Test
    @Transactional
    void delete() {
        // Добавляем пользователя
        User addedUser = userService.create("test", "password").orElseThrow();

        // Удаляем пользователя
        userService.delete(addedUser);

        // Синхронизируем состояние и очищаем кеш
        entityManager.flush();
        entityManager.clear();

        // Проверяем, что пользователя больше нет в базе
        assertFalse(userService.get(addedUser.getId()).isPresent(), "User should be deleted");
    }

    @Test
    @Transactional
    void deleteById() {
        // Добавляем пользователя
        User addedUser = userService.create("test", "password").orElseThrow();

        // Удаляем пользователя по ID
        userService.deleteById(addedUser.getId());

        // Синхронизируем состояние и очищаем кеш
        entityManager.flush();
        entityManager.clear();

        // Проверяем, что пользователя больше нет в базе
        assertFalse(userService.get(addedUser.getId()).isPresent(), "User should be deleted");
    }
}