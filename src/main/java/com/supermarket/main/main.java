package com.supermarket.main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.supermarket.DAL.MySQL;
import com.supermarket.GUI.LoginGUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class main {
        public main() {
        }

        public static void main(String[] args) {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
                UIManager.put("ProgressBar.selectionForeground", new Color(0, 0, 0));
                UIManager.put("ProgressBar.foreground", new Color(151, 180, 234));
            } catch (UnsupportedLookAndFeelException e){
                System.out.println(e.getMessage());
            }
            new LoginGUI();
//            MySQL mySQL = new MySQL();
//            try {
//                System.out.println(mySQL.executeQuery("Select * from customer"));
//
//            }catch (SQLException | IOException e) {
//                throw new RuntimeException(e);
//            }
        }
}
