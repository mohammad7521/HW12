package repository;

import models.Account;
import models.Customer;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.List;

public class CustomerRepoImpl implements BaseRepo<Customer>{

    private SessionFactory sessionFactory= HibernateUtil.getInstance();


    @Override
    public Customer add(Customer customer) {

        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(customer);
                transaction.commit();
                return customer;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public int remove(Customer customer) {
        var session = sessionFactory.openSession();
        var transaction=session.beginTransaction();

        try{
            session.remove(customer);
            transaction.commit();
            return customer.getId();
        }catch (Exception e){
            transaction.rollback();
            throw e;
        }
    }


    @Override
    public int update(Customer customer) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(customer);
                transaction.commit();
                return customer.getId();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }



    @Override
    public Customer findByID(int customerID) {
        var session=sessionFactory.openSession();
        return session.find(Customer.class,customerID);

    }



    @Override
    public List<Account> findAll() {
        return null;
    }
}
