package com.supermarket.GUI;

import com.supermarket.utils.Resource;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Thread.sleep;

public class LoginGUI extends JFrame {
    private JProgressBar progressBar;
    private JPanel contentPane;
    private JPanel header;
    private JPanel login;
    private JPanel formLogin;
    private JLabel labelLogo;
    private JLabel labelBanner_Header;
    private JLabel labelLogin;
    private JLabel labelUsername;
    private JLabel labelPassword;
    private JLabel labelForgetPasswd;
    private JTextField jTextFieldUserName;
    private JPasswordField jTextFieldPassword;
    private JButton jButtonLogin;
    private ImageIcon logo = Resource.loadImageIcon("img/logo.png");
    private ImageIcon banner_header = Resource.loadImageIcon("img/banner_header.png");
    public LoginGUI() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setSize(700, 500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        labelLogo = new JLabel(logo);
        Image imgScaled = logo.getImage().getScaledInstance(273, 267, Image.SCALE_DEFAULT | Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScaled);
        labelLogo.setIcon(scaledIcon);
        contentPane.add(labelLogo, BorderLayout.CENTER);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("open sans", Font.BOLD, 15));
        contentPane.add(progressBar, BorderLayout.SOUTH);
        Thread threadProgress = new Thread(new Runnable() {
            @Override
            public void run() {
                progress();
            }
        });
        threadProgress.start();

        header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(700, 110));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(0x018847)));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelBanner_Header = new JLabel(banner_header);
        labelBanner_Header.setSize(new Dimension(700, 100));

        Image imgScaled1 = banner_header.getImage().getScaledInstance(labelBanner_Header.getWidth(), labelBanner_Header.getHeight(), Image.SCALE_DEFAULT | Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1 = new ImageIcon(imgScaled1);
        labelBanner_Header.setIcon(scaledIcon1);

        header.add(labelBanner_Header, BorderLayout.CENTER);

        labelLogin = new JLabel("Đăng Nhập", SwingConstants.CENTER);
        labelLogin.setForeground(new Color(0x018847));
        labelLogin.setFont(new Font("Lexend", Font.BOLD, 30));
        labelLogin.setBorder(new EmptyBorder(30,0,0,0));

        login = new JPanel(new FlowLayout());


        formLogin = new JPanel(new MigLayout());
        formLogin.setPreferredSize(new Dimension(500, 270));
        login.add(formLogin);

        labelUsername = new JLabel("Tài khoản:", JLabel.LEFT);
        labelUsername.setForeground(new Color(0x018847));
        labelUsername.setPreferredSize(new Dimension(150, 50));
        labelUsername.setFont(new Font("Lexend", Font.BOLD, 15));
        formLogin.add(labelUsername);

        jTextFieldUserName = new JTextField();
        jTextFieldUserName.setBackground(new Color(211,211,211));
        jTextFieldUserName.setPreferredSize(new Dimension(350, 40));
        jTextFieldUserName.setFont(new Font("open sans", Font.PLAIN, 15));
        jTextFieldUserName.setText("Nhập tài khoản");
        jTextFieldUserName.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent ignoredEvt) {
                jTextFieldUserNameFocusGained(ignoredEvt);
            }

            public void focusLost(FocusEvent ignoredEvt) {
                jTextFieldUserNameFocusLost(ignoredEvt);
            }
        });
        jTextFieldUserName.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
        });
        formLogin.add(jTextFieldUserName, "wrap,growx");

        labelPassword = new JLabel("Mật khẩu:", JLabel.LEFT);
        labelPassword.setForeground(new Color(0x018847));
        labelPassword.setPreferredSize(new Dimension(150, 50));
        labelPassword.setFont(new Font("Lexend", Font.BOLD, 15));
        formLogin.add(labelPassword);

        jTextFieldPassword = new JPasswordField();
        jTextFieldPassword.setBackground(new Color(211,211,211));
        jTextFieldPassword.setPreferredSize(new Dimension(350, 40));
        jTextFieldPassword.setText("Password");
        jTextFieldPassword.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent ignoredEvt) {
                jTextFieldPasswordFocusGained(ignoredEvt);
            }

            public void focusLost(FocusEvent ignoredEvt) {
                jTextFieldPasswordFocusLost(ignoredEvt);
            }
        });
        jTextFieldPassword.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
        });
        formLogin.add(jTextFieldPassword, "wrap,growx");

        labelForgetPasswd = new JLabel("Quên mật khẩu?");
        labelForgetPasswd.setFont(new Font("Lexend", Font.PLAIN, 12));
        labelForgetPasswd.setBorder(new EmptyBorder(0,0,0,0));
        formLogin.add(labelForgetPasswd, "span, right, wrap");

        jButtonLogin = new JButton("Đăng nhập");
        jButtonLogin.setBackground(new Color(0x018847));;
        jButtonLogin.setForeground(new Color(0xFFFFFF));
        jButtonLogin.setFont(new Font("Lexend", Font.BOLD, 15));
        jButtonLogin.setPreferredSize(new Dimension(80, 50));
        jButtonLogin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                login();
            }
        });
        formLogin.add(jButtonLogin, "span, center, wrap");


    }

    private void jTextFieldUserNameFocusLost(FocusEvent ignoredEvt) {
        if (jTextFieldUserName.getText().isEmpty()) {
            jTextFieldUserName.setText("Nhập tài khoản");
        }
    }

    private void jTextFieldUserNameFocusGained(FocusEvent ignoredEvt) {
        if (jTextFieldUserName.getText().equals("Nhập tài khoản")) {
            jTextFieldUserName.setText("");
        }
    }

    private void jTextFieldPasswordFocusGained(FocusEvent ignoredEvt) {
        String password = new String(jTextFieldPassword.getPassword());
        if (password.equals("Password")) {
            jTextFieldPassword.setText("");
        }
    }

    private void jTextFieldPasswordFocusLost(FocusEvent ignoredEvt) {
        String password = new String(jTextFieldPassword.getPassword());
        if (password.isEmpty()) {
            jTextFieldPassword.setText("Password");
        }
    }

    private void progress() {
        int i = 0;
        while (i <= 100){
            i++;
            progressBar.setValue(i);
            try {
                sleep(15);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            sleep(200);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        contentPane.removeAll();
        contentPane.add(header, BorderLayout.NORTH);
        contentPane.add(labelLogin, BorderLayout.CENTER);
        contentPane.add(login, BorderLayout.SOUTH);
        contentPane.repaint();
        contentPane.revalidate();
    }

    public void login() {

    }
}
