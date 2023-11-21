package com.supermarket.main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.supermarket.BLL.AccountBLL;
import com.supermarket.BLL.BrandBLL;
import com.supermarket.BLL.CategoryBLL;
import com.supermarket.BLL.CustomerBLL;
import com.supermarket.DTO.Brand;
import com.supermarket.DTO.Category;
import com.supermarket.DTO.Customer;
import com.supermarket.GUI.HomeGUI;
import com.supermarket.GUI.LoginGUI;
import com.supermarket.utils.Date;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Mini_supermarketManagement {
    public static LoginGUI loginGUI;
    public static HomeGUI homeGUI;

    public static void main(String[] args) {
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("ProgressBar.selectionForeground", Color.black);
        UIManager.put("ProgressBar.selectionBackground", Color.black);
        UIManager.put( "ScrollBar.trackArc", 999 );
        UIManager.put( "ScrollBar.thumbArc", 999 );
        UIManager.put( "ScrollBar.trackInsets", new Insets( 2, 4, 2, 4 ) );
        UIManager.put( "ScrollBar.thumbInsets", new Insets( 2, 2, 2, 2 ) );
        UIManager.put( "ScrollBar.track", new Color(0xAE8EBCDA, true) );
        UIManager.put("PasswordField.showRevealButton", true);
        UIManager.put("PasswordField.capsLockIcon", new FlatSVGIcon("icon/capslock.svg"));
        UIManager.put("TitlePane.iconSize", new Dimension(25, 25));
        UIManager.put("TitlePane.iconMargins", new Insets(3, 5, 0, 20));

        homeGUI = new HomeGUI();
        homeGUI.setAccount(new AccountBLL().getAccountList().get(0));
        homeGUI.setVisible(true);
//        loginGUI = new LoginGUI();
//        CustomerBLL customerBLL = new CustomerBLL();
//        customerBLL.addCustomer(new Customer(12, "abc", false, new Date(2,2,2023), "0963", false, new Date(2,2,2023), 0, false));
//        System.out.println(customerBLL.getCustomerList());
//        customerBLL.updateCustomer(new Customer(12, "xyz", false, new Date(2,2,2023), "0963", false, new Date(2,2,2023), 0, false));
//        customerBLL.deleteCustomer(new Customer(12, "abc", false, new Date(2,2,2023), "0963", false, new Date(2,2,2023), 0, false));
//        System.out.println(customerBLL.findCustomersBy(Map.of("id", 12)));
//        System.out.println(customerBLL.findCustomers("id", "1"));
    }

    public static void exit(int status) {
        System.exit(status);
    }
}
