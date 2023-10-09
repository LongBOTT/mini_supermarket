package com.supermarket.GUI;

import com.supermarket.DTO.Account;
import com.supermarket.GUI.components.RoundedPanel;
import com.supermarket.utils.Date;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class HomeGUI extends JFrame {
    private Account account;
    private JPanel contentPanel;
    private JPanel header;
    private JPanel center;
    private JPanel left;
    private JPanel right;
    private RoundedPanel menu;
    private RoundedPanel content;
    private JLabel time;

    public HomeGUI (Account account) {
        this.account = account;
        initComponents();
        setVisible(true);
    }

    public static void main(String[] args) {
        Account account1 = new Account();
        new HomeGUI(account1);
    }
    public void initComponents() {
        setResizable(false);
        setSize(1440, 1024);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPanel = new JPanel();
        header = new JPanel();
        center = new JPanel();
        left = new JPanel();
        right = new JPanel();
        menu = new RoundedPanel();
        content = new RoundedPanel();
        time = new JLabel();

        contentPanel.setLayout(new BorderLayout());
        setContentPane(contentPanel);

        header.setLayout(null);
        header.setBackground(new Color(0x8EBCDA));
        header.setPreferredSize(new Dimension(1440, 30));
        contentPanel.add(header, BorderLayout.NORTH);

        center.setLayout(new BorderLayout());
        center.setBackground(new Color(0xFFFFFF));
        center.setPreferredSize(new Dimension(1440, 994));
        contentPanel.add(center, BorderLayout.CENTER);

        left.setLayout(new FlowLayout());
        left.setBackground(new Color(0xFFFFFF));
        left.setPreferredSize(new Dimension(250, 994));

        center.add(left, BorderLayout.WEST);

        right.setLayout(new FlowLayout());
        right.setBackground(new Color(0xFFFFFF));
        right.setPreferredSize(new Dimension(990, 994));
        center.add(right, BorderLayout.CENTER);

        menu.setLayout(new FlowLayout());
        menu.setBackground(new Color(0x8DDEAF));
        menu.setPreferredSize(new Dimension(235, 795));
        left.add(menu);

        content.setBackground(new Color(0x8DDEAF));
        content.setPreferredSize(new Dimension(1180, 795));;
        right.add(content);

        time.setBounds(30, 0, 200, 30);
        time.setFont(new Font("Public Sans", Font.PLAIN, 15));
        time.setForeground(new Color(0, 0, 0));
        header.add(time);

        Thread threadTime = new Thread(this::setTime);
        threadTime.start();


        pack();
    }

    public void setTime() {
        while (true) {
            time.setText(Date.now());
        }
    }
}
