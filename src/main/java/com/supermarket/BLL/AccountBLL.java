package com.supermarket.BLL;

import com.supermarket.DAL.AccountDAL;
import com.supermarket.DTO.Account;
import com.supermarket.utils.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountBLL extends Manager<Account> {
    private AccountDAL accountDAL;
    private List<Account> accountList;

    public AccountBLL() {
        accountDAL = new AccountDAL();
        accountList = searchAccounts("deleted = 0", "id != 0");
    }

    public AccountDAL getAccountDAL() {
        return accountDAL;
    }

    public void setAccountDAL(AccountDAL accountDAL) {
        this.accountDAL = accountDAL;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public Object[][] getData() {
        return getData(accountList);
    }

    public boolean addAccount(Account account) {
        accountList.add(account);
        return accountDAL.addAccount(account) != 0;
    }

    public boolean updateAccount(Account account) {
        accountList.set(getIndex(account, "id", accountList), account);
        return accountDAL.updateAccount(account) != 0;
    }

    public boolean updateAccountPassword(Account account) {
        accountList.set(getIndex(account, "id", accountList), account);
        return accountDAL.updateAccountPassword(account) != 0;
    }

    public boolean updateAccountLast_signed_in(Account account, DateTime dateTime) {
        accountList.set(getIndex(account, "id", accountList), account);
        return accountDAL.updateLast_signed_in(account, dateTime) != 0;
    }

    public boolean deleteAccount(Account account) {
        accountList.remove(getIndex(account, "id", accountList));
        return accountDAL.deleteAccount("id = " + account.getId()) != 0;
    }

    public List<Account> searchAccounts(String... conditions) {
        return accountDAL.searchAccounts(conditions);
    }

    public List<Account> findAccounts(String key, String value) {
        List<Account> list = new ArrayList<>();
        for (Account account : accountList) {
            if (getValueByKey(account, key).toString().toLowerCase().contains(value.toLowerCase())) {
                list.add(account);
            }
        }
        return list;
    }

    public List<Account> findAccountsBy(Map<String, Object> conditions) {
        List<Account> accounts = accountList;
        for (Map.Entry<String, Object> entry : conditions.entrySet())
            accounts = findObjectsBy(entry.getKey(), entry.getValue(), accounts);
        return accounts;
    }

    public boolean exists(Account account) {
        return !findAccountsBy(Map.of(
            "username", account.getUsername(),
            "role_id", account.getRoleID(),
            "staff_id", account.getStaffID()
        )).isEmpty();
    }

    public boolean exists(Map<String, Object> conditions) {
        if (conditions.containsKey("username") && conditions.get("username").equals("admin")) {
            return true;
        }
        return !findAccountsBy(conditions).isEmpty();
    }

    @Override
    public Object getValueByKey(Account account, String key) {
        return switch (key) {
            case "id" -> account.getId();
            case "username" -> account.getUsername();
            case "password" -> account.getPassword();
            case "role_id" -> account.getRoleID();
            case "staff_id" -> account.getStaffID();
            case "last_signed_in" -> account.getLast_signed_in();
            default -> null;
        };
    }
}
