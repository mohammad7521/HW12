package repository;

import models.Account;
import models.Customer;

import java.util.List;

public interface CustomerRepo {

    Customer add(Customer account);
    int remove(Customer account);
    int update(Customer account);
    Customer findByID(int accountID);
    List<Customer> findAll();
}
