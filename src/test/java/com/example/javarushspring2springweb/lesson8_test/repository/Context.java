package com.example.javarushspring2springweb.lesson8_test.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

//profile test, rollback db, find @SpringBootApplication, custom@Bean (if need), inject
@ActiveProfiles("test")
@Transactional
@SpringBootTest
@TestConfiguration
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public @interface Context {


}
