package com.example.javarushspring2springweb.lesson8_controllers.repository;

import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {

}
