package com.supermarket.main;

import com.supermarket.GUI.HomeGUI;
import com.supermarket.GUI.LoginGUI;

public class Mini_supermarketManagement {
    public static LoginGUI loginGUI;
    public static HomeGUI homeGUI;

    public static void start() {
        loginGUI = new LoginGUI();
    }
}
