package com.supermarket.GUI;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatProgressBarUI;
import com.supermarket.BLL.AccountBLL;
import com.supermarket.DTO.Account;
import com.supermarket.main.Mini_supermarketManagement;
import com.supermarket.utils.DateTime;
import com.supermarket.utils.Resource;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

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
    public LoginGUI() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        UIManager.put("ProgressBar.selectionForeground", Color.black);
        UIManager.put("ProgressBar.selectionBackground", Color.black);
        setSize(700, 500);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancel();
            }
        });

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(new Color(0x02723A));
        setContentPane(contentPane);

        labelLogo = new JLabel();
        labelLogo.setHorizontalAlignment(SwingConstants.CENTER);
        labelLogo.setIcon(new FlatSVGIcon("img/logo.svg"));
        contentPane.add(labelLogo, BorderLayout.CENTER);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("open sans", Font.BOLD, 15));
        progressBar.setBackground(new Color(0xFFFFFF));
        progressBar.setForeground(new Color(0x97B4EA));
        progressBar.setUI(new FlatProgressBarUI());
        contentPane.add(progressBar, BorderLayout.SOUTH);
        Thread threadProgress = new Thread(this::progress);
        threadProgress.start();
        dispose();

        header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(700, 110));
        header.setBackground(new Color(0x028948));
        header.setBorder(BorderFactory.createMatteBorder(0, -60, 5, 0, new Color(0xF0F0F0FF)));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelBanner_Header = new JLabel();
        labelBanner_Header.setSize(new Dimension(700, 100));


        labelBanner_Header.setIcon(new FlatSVGIcon("img/banner_header.svg"));
        labelBanner_Header.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(labelBanner_Header, BorderLayout.CENTER);

        labelLogin = new JLabel("Đăng Nhập", SwingConstants.CENTER);
        labelLogin.setBackground(new Color(0xF0F0F0FF));
        labelLogin.setForeground(new Color(0x028948));
        labelLogin.setFont(new Font("Lexend", Font.BOLD, 30));
        labelLogin.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, new Color(0x028948)));

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
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER)
                    login();
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
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER)
                    login();
            }
        });
        formLogin.add(jTextFieldPassword, "wrap,growx");

        labelForgetPasswd = new JLabel("Quên mật khẩu?");
        labelForgetPasswd.setFont(new Font("Lexend", Font.PLAIN, 12));
        labelForgetPasswd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelForgetPasswd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                labelForgetPasswd.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0x018847)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelForgetPasswd.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            }
            public void mousePressed(MouseEvent e) {
                forgotPassword();
            }
        });
        formLogin.add(labelForgetPasswd, "span, right, wrap");

        jButtonLogin = new JButton("Đăng nhập");
        jButtonLogin.setBackground(new Color(0x018847));;
        jButtonLogin.setForeground(new Color(0xFFFFFF));
        jButtonLogin.setFont(new Font("Lexend", Font.BOLD, 15));
        jButtonLogin.setPreferredSize(new Dimension(80, 50));
        jButtonLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                login();
            }
        });
        jButtonLogin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
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
            sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        contentPane.removeAll();
        contentPane.setBackground(new Color(0xF0F0F0));
        contentPane.add(header, BorderLayout.NORTH);
        contentPane.add(labelLogin, BorderLayout.CENTER);
        contentPane.add(login, BorderLayout.SOUTH);
        contentPane.repaint();
        contentPane.revalidate();
    }

    public void login() {
        String userName, passWord;
        userName = jTextFieldUserName.getText();
        passWord = new String(jTextFieldPassword.getPassword());
        AccountBLL accountBLL = new AccountBLL();
        List<Account> accountList = accountBLL.findAccounts("username", userName);
        if (accountList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!accountList.get(0).getPassword().equals(passWord)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Account account = accountList.get(0);
        try {
            DateTime now = DateTime.parseDateTime(String.valueOf(new DateTime()));
            accountBLL.updateAccountLast_signed_in( account, now);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JOptionPane.showMessageDialog(this, "Đăng nhập thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        try {
            Thread thread = new Thread(() -> Mini_supermarketManagement.homeGUI.setAccount(account));
            thread.start();
            thread.join();
        } catch (Exception ignored) {

        }
        dispose();
        Mini_supermarketManagement.homeGUI.setVisible(true);
    }

    private void forgotPassword() {
        new ForgotPasswordGUI();
    }

    private void cancel() {
        String[] options = new String[]{"Huỷ", "Thoát chương trình"};
        int choice = JOptionPane.showOptionDialog(null, "Bạn có muốn thoát chương trình?",
            "Lỗi", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        if (choice == 1)
            Mini_supermarketManagement.exit(1);
    }

}
