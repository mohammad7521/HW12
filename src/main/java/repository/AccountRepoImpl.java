package repository;

import com.sun.xml.fastinfoset.util.StringArray;
import models.Account;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AccountRepoImpl implements BaseRepo<Account>{

    private SessionFactory sessionFactory=HibernateUtil.getInstance();

    @Override
    public Account add(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(account);
                transaction.commit();
                return account;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public Long remove(Account account) {
        var session = sessionFactory.openSession();
        var transaction=session.beginTransaction();

        try{
            session.remove(account);
            transaction.commit();
            return account.getId();
        }catch (Exception e){
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void update(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(account);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public Account findByID(Long accountID) {
        var session=sessionFactory.openSession();
        return session.find(Account.class,accountID);

    }

    @Override
    public List<Account> findAll() {
        var session=sessionFactory.openSession();
        var transaction=session.beginTransaction();

        List<Account> accountList;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery criteria = builder.createQuery(Account.class);
            criteria.from(Account.class);
            accountList = session.createQuery(criteria).getResultList();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw e;
        }
        return accountList;

    }


    public int hqlTruncate(){

        int returnQueryInt;
        var session = sessionFactory.openSession();
        var transaction=session.beginTransaction();
        try {
            String hql = String.format("delete from %s", "Account");
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
