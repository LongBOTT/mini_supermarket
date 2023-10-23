package com.supermarket.main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.supermarket.BLL.AccountBLL;
import com.supermarket.GUI.HomeGUI;
import com.supermarket.GUI.LoginGUI;

import javax.swing.*;
import java.awt.*;

public class Mini_supermarketManagement {
    public static LoginGUI loginGUI;
    public static HomeGUI homeGUI;

    public static void main(String[] args) {
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put( "ScrollBar.trackArc", 999 );
        UIManager.put( "ScrollBar.thumbArc", 999 );
        UIManager.put( "ScrollBar.trackInsets", new Insets( 2, 4, 2, 4 ) );
        UIManager.put( "ScrollBar.thumbInsets", new Insets( 2, 2, 2, 2 ) );
        UIManager.put( "ScrollBar.track", new Color(0xAE8EBCDA, true) );
        UIManager.put("PasswordField.showRevealButton", true);
        UIManager.put("PasswordField.capsLockIcon", new FlatSVGIcon("icon/capslock.svg"));

        homeGUI = new HomeGUI();
        homeGUI.setAccount(new AccountBLL().getAccountList().get(0));
        homeGUI.setVisible(true);
//        loginGUI = new LoginGUI();
    }

    public static void exit(int status) {
        System.exit(status);
    }
}
