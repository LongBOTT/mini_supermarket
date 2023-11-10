package com.supermarket.GUI;

import com.supermarket.BLL.RoleBLL;
import com.supermarket.DTO.Account;
import com.supermarket.BLL.StaffBLL;
import com.supermarket.DTO.Role;
import com.supermarket.DTO.Staff;
import com.supermarket.GUI.components.*;
import com.supermarket.main.Mini_supermarketManagement;
import com.supermarket.utils.Date;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.supermarket.utils.DateTime;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HomeGUI extends JFrame {
    private final StaffBLL staffBLL = new StaffBLL();
    private final RoleBLL roleBLL = new RoleBLL();
    private Account account;
    private JPanel contentPanel;
    private JPanel header;
    private JPanel center;
    private JPanel left;
    private JPanel right;
    private JPanel infor;
    private RoundedPanel menu;
    private RoundedPanel content;
    private RoundedPanel function;
    private RoundedPanel[] modules = new RoundedPanel[15];
    private RoundedPanel currentModule;
    private RoundedPanel addBanner;
    private JLabel time;
    private JLabel staffName;
    private JLabel iconLogout;
    private JLabel addIcon;
    private JLabel[] moduleNames = new JLabel[15];
    public List<JLabel> banners = new ArrayList<>(0);
    private boolean over = false;
    private boolean pressover;
    private Color color;
    private Color colorOver;
    private int currentPanel = 0;
    private int currentBanner = 0;
    private Thread autoRenderBanner;
    public HomeGUI () {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
        initComponents();
        getUser();
        initMenu();
        selectRoundPanel(currentPanel);
    }


    public void getUser() {
        Staff staff = staffBLL.findStaffsBy(Map.of("id", account.getStaffID())).get(0);
        Role role = roleBLL.findRolesBy(Map.of("id", account.getRoleID())).get(0);
        staffName.setText(staff.getName() + " - " + role.getName());
    }

    public void initComponents() {
        setIconImage(new FlatSVGIcon("img/application_logo.svg").getImage());
        setTitle("Hệ thống quản lý siêu thị mini Bách Hoá Xanh");
        setResizable(false);
        setPreferredSize(new Dimension(1440, 850));
        setMinimumSize(new Dimension());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });

        contentPanel = new JPanel();
        header = new JPanel();
        center = new JPanel();
        left = new JPanel();
        right = new JPanel();
        infor = new JPanel();
        menu = new RoundedPanel();
        content = new RoundedPanel();
        function = new RoundedPanel();
        addBanner = new RoundedPanel();
        time = new JLabel();
        staffName = new JLabel();
        iconLogout = new JLabel();
        addIcon = new JLabel();
        for (int i = 0; i < modules.length; i++) {
            modules[i] = new RoundedPanel();
            moduleNames[i] = new JLabel();
        }
        for (int i = 0; i<3; i++) {
            banners.add(new JLabel());
        }
        color = new Color(0xFFFFFF);
        colorOver = new Color(0x8EBCDA);

        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(0x8DDEAF));
        setContentPane(contentPanel);

        header.setLayout(new BorderLayout());
        header.setBackground(new Color(0x8EBCDA));
        header.setPreferredSize(new Dimension(1440, 30));
        contentPanel.add(header, BorderLayout.NORTH);

        time.setBounds(30, 0, 200, 30);
        time.setBorder(new EmptyBorder(0, 50,0,0));
        time.setHorizontalAlignment(SwingConstants.LEFT);
        time.setForeground(new Color(0, 0, 0));
        time.setPreferredSize(new Dimension(350, 30));;
        time.setFont(new Font("Public Sans", Font.PLAIN, 15));
        header.add(time, BorderLayout.WEST);

        Thread threadTime = new Thread(this::setTime);
        threadTime.start();

        infor.setLayout(new BorderLayout());
        infor.setBackground(new Color(0x8EBCDA));
        infor.setPreferredSize(new Dimension(1040, 30));
        header.add(infor, BorderLayout.CENTER);

        staffName.setIcon(new FlatSVGIcon("icon/avatar.svg"));
        staffName.setBorder(new EmptyBorder(0, 0,0,20));
        staffName.setHorizontalAlignment(SwingConstants.RIGHT);
        staffName.setFont(new Font("Public Sans", Font.PLAIN, 15));
        infor.add(staffName, BorderLayout.CENTER);

        iconLogout.setIcon(new FlatSVGIcon("icon/logout.svg"));
        iconLogout.setBorder(new EmptyBorder(0, 0,0,10));
        iconLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        iconLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exit();
            }
        });
        infor.add(iconLogout, BorderLayout.EAST);

        center.setLayout(new BorderLayout());
        center.setBackground(new Color(0xFFFFFF));
        center.setPreferredSize(new Dimension(1440, 770));
        contentPanel.add(center, BorderLayout.CENTER);

        left.setLayout(new FlowLayout());
        left.setBackground(new Color(0xFFFFFF));
        left.setPreferredSize(new Dimension(250, 770));

        center.add(left, BorderLayout.WEST);

        right.setLayout(new BorderLayout());
        right.setBackground(new Color(0xFFFFFF));
        right.setPreferredSize(new Dimension(1100, 770));
        center.add(right, BorderLayout.CENTER);

        menu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        menu.setBackground(new Color(0x8EBCDA));
        menu.setPreferredSize(new Dimension(235, 770));
        menu.setAlignmentX(Component.CENTER_ALIGNMENT);

        left.add(menu);

        content.setLayout(new BorderLayout());
        content.setBorder(new EmptyBorder(5,0,5,10));
        content.setBackground(new Color(0xFFFFFF));
        content.setPreferredSize(new Dimension(1160, 770));;
        right.add(content, BorderLayout.CENTER);

        banners.get(0).setIcon(new FlatSVGIcon("img/banner1.svg"));
        banners.get(1).setIcon(new FlatSVGIcon("img/banner2.svg"));
        banners.get(2).setIcon(new FlatSVGIcon("img/banner3.svg"));

        for (JLabel label : banners) {
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                        new ManageBannersGUI();
                }
            });
        }

        addBanner.setLayout(new GridBagLayout());

        addIcon.setIcon(new FlatSVGIcon("icon/addBanner.svg"));
        addIcon.setBackground(new Color(0xF3DA9B));
        addIcon.setPreferredSize(new Dimension(70, 70));
        addIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addIcon.setHorizontalAlignment(SwingConstants.CENTER);
        addIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                new ManageBannersGUI();
            }
        });
        addBanner.add(addIcon);
    }

    private void initMenu() {
        for (int i = 0; i < modules.length; i++) {
            int index = i;
            modules[i].setLayout(new FlowLayout());
            modules[i].setPreferredSize(new Dimension(220, 41));
            modules[i].setBackground(new Color(0xFFFFFF));
            modules[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            modules[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!pressover && index != currentPanel) {
                        e.getComponent().setBackground(colorOver);
                        over = true;
                    }
                }

                public void mouseExited(MouseEvent e) {
                    if (!pressover && index != currentPanel) {
                        e.getComponent().setBackground(color);
                        over = false;
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    if (!pressover && index != currentPanel) {
                        if (over) {
                            e.getComponent().setBackground(colorOver);
                        } else {
                            e.getComponent().setBackground(color);
                        }
                    }
                }

                public void mousePressed(MouseEvent e) {
                    selectRoundPanel(index);
                }
            });
            menu.add(modules[i]);

            moduleNames[i].setPreferredSize(new Dimension(190, 35));
            moduleNames[i].setHorizontalAlignment(SwingConstants.LEFT);
            moduleNames[i].setVerticalAlignment(SwingConstants.CENTER);
            moduleNames[i].setFont((new Font("FlatLaf.style", Font.PLAIN, 15)));
            moduleNames[i].setIconTextGap(30);
            moduleNames[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            moduleNames[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!pressover && index != currentPanel) {
                        e.getComponent().getParent().setBackground(colorOver);
                        over = true;
                    }
                }

                public void mouseExited(MouseEvent e) {
                    if (!pressover && index != currentPanel) {
                        e.getComponent().getParent().setBackground(color);
                        over = false;
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    if (!pressover && index != currentPanel) {
                        if (over) {
                            e.getComponent().getParent().setBackground(colorOver);
                        } else {
                            e.getComponent().getParent().setBackground(color);
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    selectRoundPanel(index);
                }
            });
            modules[i].add(moduleNames[i]);
        }

        moduleNames[0].setIcon(new FlatSVGIcon("icon/home.svg"));
        moduleNames[0].setText("Trang chủ");

        moduleNames[1].setIcon(new FlatSVGIcon("icon/sales.svg"));
        moduleNames[1].setText("Bán hàng");

        moduleNames[2].setIcon(new FlatSVGIcon("icon/warehouse.svg"));
        moduleNames[2].setText("Kho hàng");

        moduleNames[3].setIcon(new FlatSVGIcon("icon/statistic.svg"));
        moduleNames[3].setText("Thống kê");

        moduleNames[4].setIcon(new FlatSVGIcon("icon/discount.svg"));
        moduleNames[4].setText("Giảm giá");

        moduleNames[5].setIcon(new FlatSVGIcon("icon/promotion.svg"));
        moduleNames[5].setText("Khuyến mãi");

        moduleNames[6].setIcon(new FlatSVGIcon("icon/receipt.svg"));
        moduleNames[6].setText("Hoá đơn");

        moduleNames[7].setIcon(new FlatSVGIcon("icon/import.svg"));
        moduleNames[7].setText("Phiếu nhập");

        moduleNames[8].setIcon(new FlatSVGIcon("icon/export.svg"));
        moduleNames[8].setText("Phiếu xuất");

        moduleNames[9].setIcon(new FlatSVGIcon("icon/product.svg"));
        moduleNames[9].setText("Sản phẩm");

        moduleNames[10].setIcon(new FlatSVGIcon("icon/supplier.svg"));
        moduleNames[10].setText("Nhà cung cấp");

        moduleNames[11].setIcon(new FlatSVGIcon("icon/customer.svg"));
        moduleNames[11].setText("Khách hàng");

        moduleNames[12].setIcon(new FlatSVGIcon("icon/staff.svg"));
        moduleNames[12].setText("Nhân viên");

        moduleNames[13].setIcon(new FlatSVGIcon("icon/account.svg"));
        moduleNames[13].setText("Tài khoản");

        moduleNames[14].setIcon(new FlatSVGIcon("icon/decentralization.svg"));
        moduleNames[14].setText("Phân quyền");
    }

    private void Disable() {
        if (currentModule != null) {
            currentModule.setBackground(color);
        }
    }

    private void Active(RoundedPanel module) {
        Disable();
        currentModule = module;
        module.setBackground(colorOver);
    }

    public void selectRoundPanel(int index) {
        Active(modules[index]);
        JPanel panel = switch (index) {
            case 0 -> home();
            case 1 -> new SaleGUI(account);
            case 2 -> new Layout1();
            case 3 -> new StatisticPanel();
            case 4 -> new Layout3();
            case 5 -> new Layout3();
            case 6 -> new Layout2();
            case 7 -> new Layout2();
            case 8 -> new Layout2();
            case 9 -> new Layout4();
            case 10 -> new Layout1();
            case 11 -> new Layout1();
            case 12 -> new Layout1();
            case 13 -> new AccountGUI();
            case 14 -> new Layout4();
            default -> null;
        };
        currentPanel = index;
        OpenChildForm(panel);
    }

    private JPanel home() {
        RoundedPanel panel = new RoundedPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(0x8EBCDA));
//        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        panel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                new ManageBannersGUI();
//            }
//        });
        renderBanners(panel);
        return panel;
    }

    public void renderBanners(JPanel panel){
        if (autoRenderBanner != null)
            autoRenderBanner.interrupt();
        panel.removeAll();
        if (!banners.isEmpty()) {
            currentBanner = 0;
            panel.add(banners.get(currentBanner));
        } else {
            currentBanner = -1;
            panel.add(addBanner);
        }
        panel.repaint();
        panel.revalidate();
        content.add(panel);
        autoRenderBanner = new Thread(() -> {
        DateTime start = new DateTime();
            while (!Thread.currentThread().isInterrupted()) {
                if (!banners.isEmpty()) {
                    if (DateTime.calculateTime(start, new DateTime()) == 3) {
                        if (currentBanner == banners.size()-1)
                            currentBanner = 0;
                        else
                            currentBanner += 1;
                        panel.removeAll();
                        panel.add(banners.get(currentBanner));
                        panel.repaint();
                        panel.revalidate();

                        start = new DateTime();
                    }
                } else {
                    currentBanner = -1;
                    panel.removeAll();
                    panel.add(addBanner);
                    panel.repaint();
                    panel.revalidate();
                    autoRenderBanner.interrupt();
                }
            }
        });
        autoRenderBanner.start();
    }

    private void OpenChildForm(JPanel panel) {
        content.removeAll();
        content.add(panel, BorderLayout.CENTER);
        content.repaint();
        content.revalidate();
        right.add(content);
    }

    public void setTime() {
        while (true) {
            time.setText(Date.now());
        }
    }

    private void exit() {
        int message = JOptionPane.showOptionDialog(null,
            "Bạn có chắc chắn muốn đăng xuất?",
            "Đăng xuất",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new String[]{"Đăng xuất", "Huỷ"},
            "Đăng xuất");
        if (message == JOptionPane.YES_OPTION) {
            this.dispose();
            Mini_supermarketManagement.loginGUI.setVisible(true);
        }
    }
}
