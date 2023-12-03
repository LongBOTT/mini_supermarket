package com.supermarket.BLL;

import com.supermarket.DAL.AccountDAL;
import com.supermarket.DTO.Account;
import com.supermarket.DTO.Staff;
import com.supermarket.utils.DateTime;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountBLL extends Manager<Account> {
    private AccountDAL accountDAL;
    private List<Account> accountList;

    public AccountBLL() {
        accountDAL = new AccountDAL();
        accountList = searchAccounts("deleted = 0");
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
        if(!validateUserName(account.getUsername())){
            JOptionPane.showMessageDialog(null,"Tên người không không được chứa số và ký tự đặc biệt." ,"Thông báo",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!validatePassWord(account.getPassword())){
            JOptionPane.showMessageDialog(null,"Mật khẩu phải có tối thiểu một ký hiệu đặc biệt, một chữ số, một ký tự viết thường, và một ký tự viết hoa." ,"Thông báo",JOptionPane.ERROR_MESSAGE);

        }

        if(exists(account.getUsername())){
            return false;
        }

        accountList.add(account);
        return accountDAL.addAccount(account) != 0;
    }

    public boolean updateAccount(Account account) {
        if(!validateUserName(account.getUsername())){
            JOptionPane.showMessageDialog(null,"Tên người không không được chứa số và ký tự đặc biệt." ,"Thông báo",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!validatePassWord(account.getPassword())){
            JOptionPane.showMessageDialog(null,"Mật khẩu phải có tối thiểu một ký hiệu đặc biệt, một chữ số, một ký tự viết thường, và một ký tự viết hoa." ,"Thông báo",JOptionPane.ERROR_MESSAGE);

        }
        if(exists(account.getUsername())){
            return false;
        }
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

//    public boolean exists(Account account) {
//        return !findAccountsBy(Map.of(
//            "username", account.getUsername(),
//            "role_id", account.getRoleID(),
//            "staff_id", account.getStaffID()
//        )).isEmpty();
//    }
//
//    public boolean exists(Map<String, Object> conditions) {
//        if (conditions.containsKey("username") && conditions.get("username").equals("admin")) {
//            return true;
//        }
//        return !findAccountsBy(conditions).isEmpty();
//    }
public boolean exists(String userName) {
    for (Account account : accountList) {
        if (account.getUsername().equals(userName)) {
            JOptionPane.showMessageDialog(null, "tên tài khoản đã tồn tại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        if (account.getUsername().equals("admin")) {
            JOptionPane.showMessageDialog(null, "tên tài khoản không được đặt trùng tên admin.", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return true;
        }
    }
    return false;
}


    public static boolean containsUpperCase(String str) {
        return str.chars().anyMatch(Character::isUpperCase);
    }
    public static boolean containsNumber(String str) {
        return str.chars().anyMatch(Character::isDigit);
    }
    public static boolean containsSpecial(String str) {
        return str.chars().anyMatch(c -> !(Character.isLetterOrDigit(c) || Character.isWhitespace(c)));
    }

    public static boolean containsAlphabet(String str) {
        return str.chars().anyMatch(Character::isAlphabetic);
    }


    public boolean validatePassWord(String passWord) {
        return containsUpperCase(passWord) && containsNumber(passWord) && containsSpecial(passWord);
    }
    public boolean validateUserName(String username) {
        return !containsNumber(username) && !containsSpecial(username);
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
