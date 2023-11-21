package com.supermarket.GUI.DialogGUI;

import com.supermarket.BLL.StaffBLL;
import com.supermarket.BLL.RoleBLL;
import com.supermarket.BLL.StaffBLL;
import com.supermarket.DTO.Staff;
import com.supermarket.DTO.Role;
import com.supermarket.DTO.Staff;
import com.supermarket.GUI.StaffGUI;
import com.supermarket.GUI.components.DataTable;
import com.supermarket.GUI.components.RoundedPanel;
import com.supermarket.GUI.components.RoundedScrollPane;
import com.supermarket.utils.Date;
import com.supermarket.utils.DateTime;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FormUpdateStaffGUI extends DialogForm {
    private final StaffBLL staffBLL = new StaffBLL();
    private final RoleBLL roleBLL = new RoleBLL();
    private DataTable dataTable;
    private RoundedPanel formDetail;
    private RoundedScrollPane scrollPaneFormDetail;
    private RoundedScrollPane scrollPaneDatatable;
    private java.util.List<JLabel> attributeStaff;
    private List<JTextField> jTextFieldStaff;
    private JButton buttonCancel;
    private JButton buttonUpdate;
    private Staff staff;
    public FormUpdateStaffGUI(Staff staff) {
        super();
        this.staff = staff;
        super.setTitle("Sửa nhân viên");
        init();
        containerButton.setBackground(new Color(0xFFFFFF));
        setVisible(true);
    }

    public void init() {
        dataTable = new DataTable(new Object[][] {}, new String[] {}, e -> {});
        formDetail = new RoundedPanel();
        attributeStaff = new ArrayList<>();
        jTextFieldStaff = new ArrayList<>();
        buttonCancel = new JButton("Huỷ");
        buttonUpdate = new JButton("Cập nhật");
        scrollPaneDatatable = new RoundedScrollPane(containerTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneFormDetail = new RoundedScrollPane(formDetail, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        scrollPaneDatatable.setPreferredSize(new Dimension(600, 700));
        leftContent.add(scrollPaneDatatable, BorderLayout.CENTER);
        containerTable.add(dataTable);

        scrollPaneFormDetail.setPreferredSize(new Dimension(670, 610));
        rightContent.add(scrollPaneFormDetail, BorderLayout.NORTH);

        formDetail.setBackground(new Color(0xFFBDD2DB));
        formDetail.setLayout(new MigLayout("", "50[]20[]10", "20[]20[]"));

        for (String string : new String[]{"Mã nhân viên:", "Tên nhân viên:", "Giới tính:", "Ngày sinh:", "Số điện thoại:", "Địa chỉ:", "Email:", "Ngày vào làm:"}) {
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(170, 30));
            label.setText(string);
            label.setFont((new Font("FlatLaf.style", Font.PLAIN, 16)));
            attributeStaff.add(label);
            formDetail.add(label);

            JTextField textField = new JTextField();
            if (string.equals("Mã nhân viên:")) {
                textField.setText(String.valueOf(staff.getId()));
                textField.setEnabled(false);
            }
            if (string.equals("Tên nhân viên:")) {
                textField.setText(staff.getName());
            }
            if (string.equals("Giới tính:")) {
                String gender = staff.getGender() ? "Nam":"Nữ";
                textField.setText(gender);
                textField.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(textField.getText().equals("Nam")){
                            textField.setText("Nữ");
                        }
                        else{
                            textField.setText("Nam");
                        }
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
                textField.setEnabled(false);
            }
            if (string.equals("Ngày sinh:")) {
                textField.setText(String.valueOf(staff.getBirthday()));
            }
            if (string.equals("Số điện thoại:")) {
                textField.setText(staff.getPhone());
            }

            if (string.equals("Địa chỉ:")) {
                textField.setText(staff.getAddress());
            }
            if (string.equals("Email:")) {
                textField.setText(staff.getEmail());
            }
            if (string.equals("Ngày vào làm:")) {
                textField.setText(String.valueOf(staff.getEntry_date()));
            }
            textField.setPreferredSize(new Dimension(400, 50));
            textField.setFont((new Font("FlatLaf.style", Font.BOLD, 14)));
            jTextFieldStaff.add(textField);
            formDetail.add(textField, "wrap");
        }

        buttonCancel.setPreferredSize(new Dimension(100,40));
        buttonCancel.setFont(new Font("FlatLaf.style", Font.BOLD, 15));
        buttonCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonCancel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                buttonCancel.setBackground(new Color(0xD54218));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonCancel.setBackground(null);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                refresh();
            }
        });
        containerButton.add(buttonCancel);

        buttonUpdate.setPreferredSize(new Dimension(100,40));
        buttonUpdate.setFont(new Font("FlatLaf.style", Font.BOLD, 15));
        buttonUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateStaff();
            }
        });
        containerButton.add(buttonUpdate);

    }

    private void updateStaff() {
        for (int i = 0; i < jTextFieldStaff.size(); i++) {
            if (i != 6 && jTextFieldStaff.get(i).getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin khách hàng.",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }


        try{
            int id = Integer.parseInt(jTextFieldStaff.get(0).getText());
            String staffName = jTextFieldStaff.get(1).getText();
            boolean gender = jTextFieldStaff.get(2).getText().equals("Nam")?true:false;
            Date birthDate = Date.parseDate(jTextFieldStaff.get(3).getText());
            String phoneNumber = jTextFieldStaff.get(4).getText();
            String address = jTextFieldStaff.get(5).getText();
            String email = jTextFieldStaff.get(6).getText();
            Date entry_date = Date.parseDate(jTextFieldStaff.get(7).getText());

            Staff staff = new Staff(id,staffName,gender,birthDate,phoneNumber,address,email,entry_date,false);

            String[] options = new String[]{"Huỷ", "Xác nhận"};
            int choice = JOptionPane.showOptionDialog(null, "Xác nhận cập nhật khách hàng?",
                "Thông báo", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (choice == 1) {
                if (staffBLL.updateStaff(staff)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thành công!",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    StaffGUI.loadDataTable(staffBLL.getData());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật khách hàng không thành công!",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void refresh() {
        jTextFieldStaff.get(1).setText("");
        jTextFieldStaff.get(3).setText("");
        jTextFieldStaff.get(4).setText("");
        jTextFieldStaff.get(1).setEnabled(false);
        jTextFieldStaff.get(3).setEnabled(false);
        jTextFieldStaff.get(4).setEnabled(false);
    }

    private void loadTableStaff() {
        dataTable = new DataTable(staffBLL.getData(), new String[] {"Mã nhân viên", "Họ tên", "Giới tính", "Ngày sinh", "Điện thoại", "Địa chỉ", "Email", "Ngày vào làm"}, e -> {});
        dataTable.setPreferredSize(new Dimension(1500, 700));
        containerTable.removeAll();
        containerTable.add(dataTable.getTableHeader(), BorderLayout.NORTH);
        containerTable.add(dataTable, BorderLayout.CENTER);

        Staff staff = staffBLL.findStaffsBy(Map.of("id", Integer.parseInt(jTextFieldStaff.get(5).getText()))).get(0);
        int index = staffBLL.getIndex(staff, "id", staffBLL.getStaffList());
        dataTable.setRowSelectionInterval(index, index);

        containerTable.repaint();
        containerTable.revalidate();
        scrollPaneDatatable.setViewportView(containerTable);
    }

    private void loadTableRole() {
        dataTable = new DataTable(roleBLL.getData(), new String[] {"Mã chức vụ", "Tên chức vụ"}, e -> selectRowTable());
        containerTable.removeAll();
        containerTable.add(dataTable.getTableHeader(), BorderLayout.NORTH);
        containerTable.add(dataTable, BorderLayout.CENTER);

        Role role = roleBLL.findRolesBy(Map.of("id", Integer.parseInt(jTextFieldStaff.get(3).getText()))).get(0);
        int index = roleBLL.getIndex(role, "id", roleBLL.getRoleList());
        dataTable.setRowSelectionInterval(index, index);

        containerTable.repaint();
        containerTable.revalidate();
        scrollPaneDatatable.setViewportView(containerTable);
    }

    public void selectRowTable() {
        String id = "";
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        int indexRow = dataTable.getSelectedRow();
        id = model.getDataVector().elementAt(indexRow).get(0).toString();
        jTextFieldStaff.get(3).setEnabled(true);
        jTextFieldStaff.get(3).setText(id);
        jTextFieldStaff.get(3).setEnabled(false);
    }
}
