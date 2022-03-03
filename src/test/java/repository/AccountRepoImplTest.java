package repository;

import models.Account;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import util.HibernateUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepoImplTest {

    private AccountRepoImpl accountRepoImpl=new AccountRepoImpl();
    @BeforeAll
    static void setUp(){
        var sessionFactory=HibernateUtil.getInstance();
    }

//    @BeforeEach
//    void beforeEach() {
//
//    }



    //testing add and findByID at the same time
    @Test
    void addAndFindByID() {

        //arrange
        var account=new Account(null,2000,2,123412341234L,2);

        //act
        accountRepoImpl.add(account);

        //assert
        Account loadedAccount= (Account) accountRepoImpl.findByID(account.getId());
        assertAll(
                ()->assertNotNull(loadedAccount),
                ()->assertEquals(loadedAccount.getId(),account.getId()),
                ()->assertEquals(loadedAccount.getBalance(),account.getBalance()),
                ()->assertEquals(loadedAccount.getCreditCardNumber(),account.getCreditCardNumber()),
                ()->assertEquals(loadedAccount.getBranchID(),account.getBranchID()),
                ()->assertEquals(loadedAccount.getClientID(),account.getClientID())
        );

    }



    @Test
    void findAll() {
        //arrange
        Account acc1=new Account(null,100,1,1234,1);
        Account acc2=new Account(null,200,2,4321,1);
        Account acc3=new Account(null,300,3,2341,1);

        //act
        accountRepoImpl.add(acc1);
        accountRepoImpl.add(acc2);
        accountRepoImpl.add(acc3);


        //assert
        List<Account> loadedAccounts=accountRepoImpl.findAll();

        //checking if all accounts are added all accounts are added
        assertEquals(3,loadedAccounts.size());

        assertAll(
                ()->assertNotNull(loadedAccounts),
                ()->assertEquals(loadedAccounts.get(0).getId(),acc1.getId()),
                ()->assertEquals(loadedAccounts.get(0).getBalance(),acc1.getBalance()),
                ()->assertEquals(loadedAccounts.get(0).getCreditCardNumber(),acc1.getCreditCardNumber()),
                ()->assertEquals(loadedAccounts.get(0).getBranchID(),acc1.getBranchID()),
                ()->assertEquals(loadedAccounts.get(0).getClientID(),acc1.getClientID())
        );
    }



    @Test
    void remove() {

        //arrange
        var account=new Account(null,12,1223,12323,223234);


        //act
        accountRepoImpl.add(account);


        //assert
        accountRepoImpl.remove(account);
        List<Account> accountList=accountRepoImpl.findAll();
        assertEquals(0,accountList.size());

    }



    @Test
    void update() {
        //arrange
        var account=new Account(null,2323,1212,23,121);

        //act
        accountRepoImpl.add(account);
        account.setBalance(213243);
        account.setCreditCardNumber(2141354235);
        accountRepoImpl.update(account);

        //assert
        var loadedAccount=accountRepoImpl.findByID(account.getId());
        assertAll(
                ()->assertNotNull(loadedAccount),
                ()->assertEquals(loadedAccount.getBalance(),account.getBalance()),
                ()->assertEquals(loadedAccount.getCreditCardNumber(),account.getCreditCardNumber()),
                ()->assertEquals(loadedAccount.getBranchID(),account.getBranchID()),
                ()->assertEquals(loadedAccount.getClientID(),account.getClientID())
        );

    }



    @AfterEach
    void tearDown() {
        accountRepoImpl.hqlTruncate();
    }


}