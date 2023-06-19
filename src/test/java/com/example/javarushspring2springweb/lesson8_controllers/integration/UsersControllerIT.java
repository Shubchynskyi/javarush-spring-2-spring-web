package com.example.javarushspring2springweb.lesson8_controllers.integration;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MyTestContext
@RequiredArgsConstructor
//@AutoConfigureMockMvc
public class UsersControllerIT {

    private final WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testGetUsers() throws Exception {
        // выполняем GET запрос
        mockMvc.perform(get("/users"))
                // проверяем, что статус ответа - 200 (OK)
                .andExpect(status().isOk())
                // проверяем, что возвращается правильный вид
                .andExpect(view().name("users"))
                // можно добавить проверку атрибутов модели
                .andExpect(model().attributeExists("users"));
    }

    @Test
    public void testCreateUser() throws Exception {
        // выполняем POST запрос
        mockMvc.perform(post("/users")
                        .param("name", "test")
                        .param("password", "password"))
                // проверяем, что статус ответа - 302 (Redirect)
                .andExpect(status().is3xxRedirection())
                // проверяем, что производится редирект на /users
                .andExpect(redirectedUrl("users"));
    }
}