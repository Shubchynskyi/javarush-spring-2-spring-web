package com.example.javarushspring2springweb.repository;

import java.util.Collection;
import java.util.List;

public interface AbstractRepo<T> {
    T getById(Long id);
    List<T> getAll();
}
