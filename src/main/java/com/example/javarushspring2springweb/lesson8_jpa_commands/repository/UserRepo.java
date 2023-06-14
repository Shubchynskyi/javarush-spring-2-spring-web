package com.example.javarushspring2springweb.lesson8_jpa_commands.repository;

import com.example.javarushspring2springweb.lesson8_jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {

}
