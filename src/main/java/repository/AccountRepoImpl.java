package repository;

import models.Account;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.List;

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
    public int remove(Account account) {
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
    public int update(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(account);
                transaction.commit();
                return account.getId();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public Account findByID(int accountID) {
        var session=sessionFactory.openSession();
        return session.find(Account.class,accountID);

    }

    @Override
    public List<Account> findAll() {
        return null;
    }
}
