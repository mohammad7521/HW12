package repository;

import models.Account;
import models.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepoImplTest {

    private CustomerRepoImpl customerRepo=new CustomerRepoImpl();

    @BeforeAll
    static void setUp(){
        var sessionFactory= HibernateUtil.getInstance();
    }

//    @BeforeEach
//    void beforeEach() {
//
//    }



    //testing add and findByID at the same time
    @Test
    void addAndFindByID() {

        //arrange
        var customer=new Customer(null,"mohammad","mohammadi","tehran");


        //act
        customerRepo.add(customer);

        //assert
        Customer loadedCustomer= (Customer) customerRepo.findByID(customer.getId());
        assertAll(
                ()->assertNotNull(loadedCustomer),
                ()->assertEquals(loadedCustomer.getId(),customer.getId()),
                ()->assertEquals(loadedCustomer.getAddress(),customer.getAddress()),
                ()->assertEquals(loadedCustomer.getFirstName(),customer.getFirstName()),
                ()->assertEquals(loadedCustomer.getLastName(),customer.getLastName())
        );

    }



    @Test
    void findAll() {
        //arrange
        Customer customer1=new Customer(null,"ahad","javid","esfahan");
        Customer customer2=new Customer(null,"reza","ahmadi","shiraz");
        Customer customer3=new Customer(null,"reza","mohseni","ahvaz");

        //act
        customerRepo.add(customer1);
        customerRepo.add(customer2);
        customerRepo.add(customer3);


        //assert
        List<Customer> loadedCustomers=customerRepo.findAll();

        //checking if all Customers are added all Customers are added
        assertEquals(3,loadedCustomers.size());

        assertAll(
                ()->assertNotNull(loadedCustomers),
                ()->assertEquals(loadedCustomers.get(0).getId(),customer1.getId()),
                ()->assertEquals(loadedCustomers.get(0).getAddress(),customer1.getAddress()),
                ()->assertEquals(loadedCustomers.get(0).getFirstName(),customer1.getFirstName()),
                ()->assertEquals(loadedCustomers.get(0).getLastName(),customer1.getLastName())
        );
    }



    @Test
    void remove() {

        //arrange
        var customer=new Customer(null,"mohammad","mohammadi","tehran");


        //act
        customerRepo.add(customer);


        //assert
        customerRepo.remove(customer);
        List<Customer> customerList=customerRepo.findAll();
        assertEquals(0,customerList.size());

    }



    @Test
    void update() {
        //arrange
        var customer=new Customer(null,"mohammad","mohammadi","ahwaz");

        //act
        customerRepo.add(customer);
        customer.setFirstName("reza");
        customer.setLastName("razavi");
        customerRepo.update(customer);

        //assert
        var loadedCustomer=customerRepo.findByID(customer.getId());
        assertAll(
                ()->assertNotNull(loadedCustomer),
                ()->assertEquals(loadedCustomer.getId(),customer.getId()),
                ()->assertEquals(loadedCustomer.getAddress(),customer.getAddress()),
                ()->assertEquals(loadedCustomer.getFirstName(),customer.getFirstName()),
                ()->assertEquals(loadedCustomer.getLastName(),customer.getLastName())
        );

    }



    @AfterEach
    void tearDown() {
        customerRepo.hqlTruncate();
    }

}