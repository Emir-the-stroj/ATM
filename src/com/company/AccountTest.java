package com.company;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class AccountTest {
Account account;
double balance;

    @Before
    public void setUp(){
        this.account = new Account();
        account.setAmmountOnAccount(100);
        this.balance = 50;
    }
    @org.junit.Test
    public void ifBalanceis50then150() {
        account.addFunds(balance);
       assertEquals(150, account.getAmmountOnAccount(), Math.abs(150 - account.getAmmountOnAccount()));
    }

    @org.junit.Test
    public void ifBalanceis50then50() {
    account.removeFunds(balance);
    assertEquals(50, account.getAmmountOnAccount(), Math.abs(50 - account.getAmmountOnAccount()));
    }



    @org.junit.Test
    public void testSetAccountOwner() {
        account.setAccountOwner("Rocky Balboa");
        String name = account.getAccountOwner();
        assertEquals("Rocky Balboa", name);
    }


}
