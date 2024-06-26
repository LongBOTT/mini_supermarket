package com.supermarket.GUI;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.supermarket.BLL.*;
import com.supermarket.BLL.StaffBLL;
import com.supermarket.DTO.Staff;
import com.supermarket.DTO.Role;
import com.supermarket.DTO.Staff;
import com.supermarket.GUI.DialogGUI.FormAddStaffGUI;
import com.supermarket.GUI.DialogGUI.FormDetailStaffGUI;
import com.supermarket.GUI.DialogGUI.FormUpdateStaffGUI;
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

public class StaffGUI extends Layout1 {
    private RoleBLL roleBLL = new RoleBLL();
    private StaffBLL staffBLL = new StaffBLL();
    private AccountBLL accountBLL = new AccountBLL();
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
    private JComboBox cbbGender;
    private JComboBox cbbMembership;
    private Object[][] staffList;

    public StaffGUI() {
        super();
        init();

    }

    public void init() {
        staffList = new Object[0][0];
        dataTable = new DataTable(new Object[][] {},
            new String[] {"Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Số điện thoại","Địa chỉ","Email","Ngày vào làm"}, e -> {});
        scrollPane = new RoundedScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        iconDetail = new JLabel();
        iconAdd = new JLabel();
        iconEdit = new JLabel();
        iconDelete = new JLabel();
        iconPDF = new JLabel();
        iconExcel = new JLabel();
        jTextFieldSearch = new JTextField();
        cbbAttributeProduct = new JComboBox(new String[] {"Tên nhân viên", "Giới tính"});
        cbbGender = new JComboBox<>();
        cbbMembership = new JComboBox<>();
//

        iconDetail.setIcon(new FlatSVGIcon("icon/detail.svg"));
        iconDetail.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iconDetail.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showDetailStaff();
            }
        });
        leftMenu.add(iconDetail);

        iconAdd.setIcon(new FlatSVGIcon("icon/add.svg"));
        iconAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iconAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                addStaff();
            }
        });
        leftMenu.add(iconAdd);

        iconEdit.setIcon(new FlatSVGIcon("icon/edit.svg"));
        iconEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iconEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateStaff();
            }
        });
        leftMenu.add(iconEdit);

        iconDelete.setIcon(new FlatSVGIcon("icon/remove.svg"));
        iconDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iconDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                deleteStaff();
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

        cbbGender.addItem("Nam");
        cbbGender.addItem("Nữ");
        rightMenu.add(cbbGender);
        cbbGender.setVisible(false);
        cbbGender.addActionListener(e -> searchByGender());
//
        cbbMembership.addItem("Là thành viên");
        cbbMembership.addItem("Không là thành viên");
        rightMenu.add(cbbMembership);
        cbbMembership.setVisible(false);
        cbbMembership.addActionListener(e -> searchByMembership());
        jTextFieldSearch.setPreferredSize(new Dimension(200, 30));
        jTextFieldSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchStaffs();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchStaffs();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchStaffs();
            }
        });
        rightMenu.add(jTextFieldSearch);

        bottom.add(scrollPane, BorderLayout.CENTER);

        loadDataTable(staffBLL.getData());
    }

    private void searchStaffs() {
        if (jTextFieldSearch.getText().isEmpty()) {
            loadDataTable(staffBLL.getData());
        } else {
            loadDataTable(staffBLL.getData(staffBLL.findStaffs("name", jTextFieldSearch.getText())));
        }
    }

    private void searchByGender() {
        String isMale = "Nam";
        String isFamele = "Nữ";
        if(isMale.equals(cbbGender.getSelectedItem())){
            loadDataTable(staffBLL.getData(staffBLL.findStaffsBy(Map.of("gender", true))));
        }else{
            loadDataTable(staffBLL.getData(staffBLL.findStaffsBy(Map.of("gender", false))));
        }
    }

    private void searchByMembership() {
        String isMembership = "Là thành viên";
        String isntMembership = "Không là thành viên";
        if(isMembership.equals(cbbMembership.getSelectedItem())){
            loadDataTable(staffBLL.getData(staffBLL.findStaffsBy(Map.of("membership", true))));
        }else{
            loadDataTable(staffBLL.getData(staffBLL.findStaffsBy(Map.of("membership", false))));
        }

    }

    private void selectSearchFilter() {
        if (Objects.requireNonNull(cbbAttributeProduct.getSelectedItem()).toString().contains("KH thành viên")) {
            jTextFieldSearch.setVisible(false);
            cbbGender.setVisible(false);
            cbbMembership.setSelectedIndex(0);
            cbbMembership.setVisible(true);
            searchByMembership();
        }else if (Objects.requireNonNull(cbbAttributeProduct.getSelectedItem()).toString().contains("Giới tính")) {
            jTextFieldSearch.setVisible(false);
            cbbMembership.setVisible(false);
            cbbGender.setSelectedIndex(0);
            cbbGender.setVisible(true);
            searchByGender();
        } else {
            cbbGender.setVisible(false);
            cbbMembership.setVisible(false);
            jTextFieldSearch.setVisible(true);
            searchStaffs();
        }

    }

    private void addStaff() {
        new FormAddStaffGUI();
    }
//    private void updateStaff(){ new FormUpdateStaffGUI();}

    private void showDetailStaff() {
        staffBLL = new StaffBLL();
        if (dataTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xem chi tiết.",
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        new FormDetailStaffGUI(staffBLL.findStaffsBy(Map.of("id", Integer.parseInt(model.getValueAt(dataTable.getSelectedRow(), 0).toString()))).get(0));
    }

    private void updateStaff() {
        staffBLL = new StaffBLL();
        if (dataTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần cập nhật.",
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        new FormUpdateStaffGUI(staffBLL.findStaffsBy(Map.of("id", Integer.parseInt(model.getValueAt(dataTable.getSelectedRow(), 0).toString()))).get(0));

    }

    private void deleteStaff() {
        if (dataTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xoá.",
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();

        String[] options = new String[]{"Huỷ", "Xác nhận"};
        int choice = JOptionPane.showOptionDialog(null, "Xác nhận xoá nhân viên?",
            "Thông báo", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice == 1) {
            if (staffBLL.deleteStaff(staffBLL.findStaffsBy(Map.of("id", Integer.parseInt(model.getValueAt(dataTable.getSelectedRow(), 0).toString()))).get(0))) {
                JOptionPane.showMessageDialog(null, "Xoá nhân viên thành công!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                loadDataTable(staffBLL.getData());
            } else {
                JOptionPane.showMessageDialog(null, "Xoá nhân viên không thành công!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void loadDataTable(Object[][] objects) {
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        model.setRowCount(0);
        for (Object[] object : objects) {
            model.addRow(object);
        }
    }
}
