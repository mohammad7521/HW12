package repository;

import models.Account;

import java.util.List;

public interface BaseRepo<T> {

    T add(T t);
    Long remove(T t);
    void update(T t);
    T findByID(Long id);
    List<T> findAll();
}
