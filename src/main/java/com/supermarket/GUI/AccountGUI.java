package com.supermarket.GUI;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.supermarket.BLL.AccountBLL;
import com.supermarket.BLL.RoleBLL;
import com.supermarket.BLL.StaffBLL;
import com.supermarket.DTO.Brand;
import com.supermarket.DTO.Category;
import com.supermarket.DTO.Role;
import com.supermarket.DTO.Staff;
import com.supermarket.GUI.DialogGUI.FormAddAccountGUI;
import com.supermarket.GUI.DialogGUI.FormDetailAccountGUI;
import com.supermarket.GUI.DialogGUI.FormUpdateAccountGUI;
import com.supermarket.GUI.components.DataTable;
import com.supermarket.GUI.components.Layout1;
import com.supermarket.GUI.components.RoundedScrollPane;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Objects;

public class AccountGUI extends Layout1 {
    private RoleBLL roleBLL = new RoleBLL();
    private StaffBLL staffBLL = new StaffBLL();
    private AccountBLL accountBLL = new AccountBLL();
    private JLabel iconDetail;
    private JLabel iconAdd;
    private JLabel iconEdit;
    private JLabel iconDelete;
    private static DataTable dataTable;
    private RoundedScrollPane scrollPane;
    private JTextField jTextFieldSearch;
    private JComboBox cbbAttributeAccount;
    private JComboBox cbbRole;
    private JComboBox cbbStaff;
    private Object[][] accountList;

    public AccountGUI() {
        super();
        init();
    }

    public void init() {
        accountList = new Object[0][0];
        dataTable = new DataTable(new Object[][] {},
            new String[] {"Mã tài khoản", "Tên tài khoản", "Mật khẩu", "Chức vụ", "Lần đăng nhập cuối", "Nhân viên"}, e -> {});
        scrollPane = new RoundedScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        iconDetail = new JLabel();
        iconAdd = new JLabel();
        iconEdit = new JLabel();
        iconDelete = new JLabel();
        jTextFieldSearch = new JTextField();
        cbbAttributeAccount = new JComboBox(new String[] {"Tên tài khoản", "Chức vụ", "Nhân viên"});
        cbbRole = new JComboBox<>();
        cbbStaff = new JComboBox<>();

        iconDetail.setIcon(new FlatSVGIcon("icon/detail.svg"));
        iconDetail.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iconDetail.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showDetailAccount();
            }
        });
        leftMenu.add(iconDetail);

        iconAdd.setIcon(new FlatSVGIcon("icon/add.svg"));
        iconAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iconAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                addAccount();
            }
        });
        leftMenu.add(iconAdd);

        iconEdit.setIcon(new FlatSVGIcon("icon/edit.svg"));
        iconEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iconEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateAccount();
            }
        });
        leftMenu.add(iconEdit);

        iconDelete.setIcon(new FlatSVGIcon("icon/remove.svg"));
        iconDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iconDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                deleteAccount();
            }
        });
        leftMenu.add(iconDelete);

        cbbAttributeAccount.setPreferredSize(new Dimension(130, 30));
        cbbAttributeAccount.addActionListener(e -> selectSearchFilter());
        rightMenu.add(cbbAttributeAccount);

        for (Role role : roleBLL.getRoleList()) {
            cbbRole.addItem(role.getName());
        }
        rightMenu.add(cbbRole);
        cbbRole.setVisible(false);
        cbbRole.addActionListener(e -> searchByRole());

        for (Staff staff : staffBLL.getStaffList()) {
            cbbStaff.addItem(staff.getName());
        }
        rightMenu.add(cbbStaff);
        cbbStaff.setVisible(false);
        cbbStaff.addActionListener(e -> searchByStaff());
        jTextFieldSearch.setPreferredSize(new Dimension(200, 30));
        jTextFieldSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchAccounts();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAccounts();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAccounts();
            }
        });
        rightMenu.add(jTextFieldSearch);

        bottom.add(scrollPane, BorderLayout.CENTER);

        loadDataTable(accountBLL.getData());
    }

    private void searchAccounts() {
        if (jTextFieldSearch.getText().isEmpty()) {
            loadDataTable(accountBLL.getData());
        } else {
            loadDataTable(accountBLL.getData(accountBLL.findAccounts("username", jTextFieldSearch.getText())));
        }
    }

    private void searchByStaff() {
        for (Staff staff : staffBLL.getStaffList()) {
            if (staff.getName().equals(cbbStaff.getSelectedItem())) {
                loadDataTable(accountBLL.getData(accountBLL.findAccountsBy(Map.of("staff_id", staff.getId()))));
                return;
            }
        }
    }

    private void searchByRole() {
        for (Role role : roleBLL.getRoleList()) {
            if (role.getName().equals(cbbRole.getSelectedItem())) {
                loadDataTable(accountBLL.getData(accountBLL.findAccountsBy(Map.of("role_id", role.getId()))));
                return;
            }
        }
    }

    private void selectSearchFilter() {
        if (Objects.requireNonNull(cbbAttributeAccount.getSelectedItem()).toString().contains("Chức vụ")) {
            jTextFieldSearch.setVisible(false);
            cbbStaff.setVisible(false);
            cbbRole.setSelectedIndex(0);
            cbbRole.setVisible(true);
            searchByRole();
        } else if (Objects.requireNonNull(cbbAttributeAccount.getSelectedItem()).toString().contains("Nhân viên")) {
            jTextFieldSearch.setVisible(false);
            cbbRole.setVisible(false);
            cbbStaff.setSelectedIndex(0);
            cbbStaff.setVisible(true);
            searchByStaff();
        } else {
            cbbRole.setVisible(false);
            cbbStaff.setVisible(false);
            jTextFieldSearch.setVisible(true);
            searchAccounts();
        }
    }

    private void addAccount() {
        new FormAddAccountGUI();
    }

    private void showDetailAccount() {
        accountBLL = new AccountBLL();
        if (dataTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần xem chi tiết.",
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        new FormDetailAccountGUI(accountBLL.findAccountsBy(Map.of("id", Integer.parseInt(model.getValueAt(dataTable.getSelectedRow(), 0).toString()))).get(0));
    }

    private void updateAccount() {
        accountBLL = new AccountBLL();
        if (dataTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần cập nhật.",
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        new FormUpdateAccountGUI(accountBLL.findAccountsBy(Map.of("id", Integer.parseInt(model.getValueAt(dataTable.getSelectedRow(), 0).toString()))).get(0));

    }

    private void deleteAccount() {
        if (dataTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần xoá.",
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();

        String[] options = new String[]{"Huỷ", "Xác nhận"};
        int choice = JOptionPane.showOptionDialog(null, "Xác nhận xoá tài khoản?",
            "Thông báo", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice == 1) {
            if (accountBLL.deleteAccount(accountBLL.findAccountsBy(Map.of("id", Integer.parseInt(model.getValueAt(dataTable.getSelectedRow(), 0).toString()))).get(0))) {
                JOptionPane.showMessageDialog(null, "Xoá tài khoản thành công!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                loadDataTable(accountBLL.getData());
            } else {
                JOptionPane.showMessageDialog(null, "Xoá khoản không thành công!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void loadDataTable(Object[][] objects) {
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        RoleBLL roleBLL = new RoleBLL();
        StaffBLL staffBLL = new StaffBLL();
        for (Object[] object : objects) {
            int roleId = Integer.parseInt(object[3].toString());
            int staffId = Integer.parseInt(object[5].toString());
            object[3] = roleBLL.findRolesBy(Map.of("id", roleId)).get(0).getName();
            object[5] = staffBLL.findStaffsBy(Map.of("id", staffId)).get(0).getName();
        }

        model.setRowCount(0);
        for (Object[] object : objects) {
            model.addRow(object);
        }
    }
}
