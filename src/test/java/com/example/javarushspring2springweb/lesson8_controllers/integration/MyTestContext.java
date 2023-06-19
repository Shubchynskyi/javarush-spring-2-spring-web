package com.example.javarushspring2springweb.lesson8_controllers.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

@ActiveProfiles("test")     //профиль для тестирования
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL) //что бы нормально инжектилось
@SpringBootTest     //позволяет создавать бины как в конфиге, например тестового пользователя
@TestConfiguration
public @interface MyTestContext {

}
