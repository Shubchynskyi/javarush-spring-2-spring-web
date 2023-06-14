package com.example.javarushspring2springweb.lessons1_7.repository;

import java.util.List;

public interface AbstractRepo<T> {
    T getById(Long id);

    List<T> getAll();

    void create(T type);
}
