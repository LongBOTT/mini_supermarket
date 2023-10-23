package com.supermarket.test;

import com.supermarket.BLL.AccountBLL;
import com.supermarket.DTO.Account;
import com.supermarket.utils.DateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AccountBLLTest {
    private static AccountBLL accountBLL;
    @BeforeAll
    public static void setup(){
        accountBLL = new AccountBLL();
    }

    @Test
    public void testList(){
        for(Account account : accountBLL.getAccountList()){
            System.out.println(
                account.getId()+" "+
                account.getUsername()+" "
                );
        }
    }
    private Account newAccount = new Account(3,"testAccount","Abc123456",1,3,new DateTime(),false);

    @Test
    public void testAdd(){
        boolean success = accountBLL.addAccount(newAccount);
        assertTrue(success);
    }
    @Test
    public void testUpdate(){
        newAccount.setPassword("hiengay");
        boolean success = accountBLL.updateAccount(newAccount);
        assertTrue(success);
    }
    @Test void testSearch(){
        List<Account> list = accountBLL.findAccountsBy(Map.of("username","testAccount"));
        for(Account account : list){
            System.out.println(account.getId() + " " + account.getUsername());
        }
    }
}
