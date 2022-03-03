package repository;

import models.Account;
import models.Customer;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
    public Long remove(Customer customer) {
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
    public void update(Customer customer) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(customer);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }



    @Override
    public Customer findByID(Long customerID) {
        var session=sessionFactory.openSession();
        return session.find(Customer.class,customerID);

    }



    @Override
    public List<Customer> findAll() {
        var session=sessionFactory.openSession();
        var transaction=session.beginTransaction();

        List<Customer> customerList;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery criteria = builder.createQuery(Customer.class);
            criteria.from(Customer.class);
            customerList = session.createQuery(criteria).getResultList();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw e;
        }
        return customerList;
    }



    public int hqlTruncate(){

        int returnQueryInt;
        var session = sessionFactory.openSession();
        var transaction=session.beginTransaction();
        try {
            String hql = String.format("delete from %s", "Customer");
            Query query = session.createQuery(hql);
            returnQueryInt=query.executeUpdate();
            transaction.commit();

        }catch (Exception e){
            transaction.rollback();
            throw e;
        }

        return returnQueryInt;

    }
}
