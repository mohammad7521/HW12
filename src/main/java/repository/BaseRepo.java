package repository;

import models.Account;

import java.util.List;

public interface BaseRepo<T> {

    T add(T t);
    int remove(T t);
    int update(T t);
    T findByID(int id);
    List<Account> findAll();
}
