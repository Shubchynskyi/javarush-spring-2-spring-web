package com.example.javarushspring2springweb.repository;

import java.util.Collection;

public interface AbstractRepo<T> {
    T getById(Long id);
    Collection<T> getAll();
}
