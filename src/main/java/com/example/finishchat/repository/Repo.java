package com.example.finishchat.repository;

import java.util.List;
import java.util.Optional;

public interface Repo <K, T>{

    void save(T entity);

    List<T> findAll();

    Optional<T> findById(K id);

    boolean delete(K id);

    void update(T entity);
}
