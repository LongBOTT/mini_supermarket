package com.supermarket.GUI;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.supermarket.BLL.AccountBLL;
import com.supermarket.BLL.CustomerBLL;
import com.supermarket.BLL.RoleBLL;
import com.supermarket.BLL.StaffBLL;
import com.supermarket.DTO.Role;
import com.supermarket.DTO.Staff;
import com.supermarket.GUI.DialogGUI.FormAddAccountGUI;
import com.supermarket.GUI.DialogGUI.FormAddCustomerGUI;
import com.supermarket.GUI.DialogGUI.FormDetailCustomerGUI;
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

public class CustomerGUI extends Layout1 {
    private RoleBLL roleBLL = new RoleBLL();
    private StaffBLL staffBLL = new StaffBLL();
    private AccountBLL accountBLL = new AccountBLL();
    private CustomerBLL customerBLL = new CustomerBLL();
    private JLabel iconDetail;
    private JLabel iconAdd;
    private JLabel iconEdit;
    private JLabel iconDelete;
    private JLabel iconPDF;
    private JLabel iconExcel;
    private static DataTable dataTable;
    private RoundedScrollPane scrollPane;
    private JTextField jTextFieldSearch;
    private JComboBox cbbAttributeProduct;
    private JComboBox cbbRole;
    private JComboBox cbbStaff;
    private Object[][] accountList;

    public CustomerGUI() {
        super();
        init();

    }

    public void init() {
        accountList = new Object[0][0];
        dataTable = new DataTable(new Object[][] {},
            new String[] {"Mã khách hàng", "Tên khách hàng", "Giới tính", "Ngày sinh", "Số điện thoại", "Thành viên","Lần đăng nhập cuối","Điểm thưởng"}, e -> {});
        scrollPane = new RoundedScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        iconDetail = new JLabel();
        iconAdd = new JLabel();
        iconEdit = new JLabel();
        iconDelete = new JLabel();
        iconPDF = new JLabel();
        iconExcel = new JLabel();
        jTextFieldSearch = new JTextField();
        cbbAttributeProduct = new JComboBox(new String[] {"Tên khách hàng", "Số điện thoại"});
        cbbRole = new JComboBox<>();
        cbbStaff = new JComboBox<>();
//

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


        iconPDF.setIcon(new FlatSVGIcon("icon/pdf.svg"));
        iconPDF.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftMenu.add(iconPDF);

        iconExcel.setIcon(new FlatSVGIcon("icon/excel.svg"));
        iconExcel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftMenu.add(iconExcel);

        cbbAttributeProduct.setPreferredSize(new Dimension(130, 30));
        cbbAttributeProduct.addActionListener(e -> selectSearchFilter());
        rightMenu.add(cbbAttributeProduct);

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

        loadDataTable(customerBLL.getData());
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
        if (Objects.requireNonNull(cbbAttributeProduct.getSelectedItem()).toString().contains("Chức vụ")) {
            jTextFieldSearch.setVisible(false);
            cbbStaff.setVisible(false);
            cbbRole.setSelectedIndex(0);
            cbbRole.setVisible(true);
            searchByRole();
        } else if (Objects.requireNonNull(cbbAttributeProduct.getSelectedItem()).toString().contains("Nhân viên")) {
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
        new FormAddCustomerGUI();
    }

    private void showDetailAccount() {
        customerBLL = new CustomerBLL();
        if (dataTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần xem chi tiết.",
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        new FormDetailCustomerGUI(customerBLL.findCustomersBy(Map.of("id", Integer.parseInt(model.getValueAt(dataTable.getSelectedRow(), 0).toString()))).get(0));
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
//        CustomerBLL customerBLL = new CustomerBLL();
//        for (Object[] object : objects) {
//            int roleId = Integer.parseInt(object[3].toString());
//            int staffId = Integer.parseInt(object[5].toString());
//            object[3] = roleBLL.findRolesBy(Map.of("id", roleId)).get(0).getName();
//            object[5] = staffBLL.findStaffsBy(Map.of("id", staffId)).get(0).getName();
//        }

        model.setRowCount(0);
        for (Object[] object : objects) {
            model.addRow(object);
        }
    }
}
