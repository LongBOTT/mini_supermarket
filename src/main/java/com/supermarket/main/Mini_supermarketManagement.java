package com.supermarket.main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.supermarket.BLL.AccountBLL;
import com.supermarket.DTO.Account;
import com.supermarket.GUI.HomeGUI;
import com.supermarket.GUI.LoginGUI;

public class Mini_supermarketManagement {
    public static LoginGUI loginGUI;
    public static HomeGUI homeGUI;

    public static void main(String[] args) {
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        homeGUI = new HomeGUI();
//        homeGUI.setAccount(new AccountBLL().getAccountList().get(0));
//        homeGUI.setVisible(true);
        loginGUI = new LoginGUI();
    }

    public static void exit(int status) {
        System.exit(status);
    }
}
