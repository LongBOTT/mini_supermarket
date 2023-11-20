package com.supermarket.GUI.DialogGUI;


import java.time.format.DateTimeFormatter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import com.supermarket.BLL.StaffBLL;
import com.supermarket.BLL.RoleBLL;
import com.supermarket.BLL.StaffBLL;
import com.supermarket.DTO.Staff;
import com.supermarket.DTO.Role;
import com.supermarket.DTO.Staff;
import com.supermarket.GUI.StaffGUI;
import com.supermarket.GUI.LoginGUI;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FormAddStaffGUI extends DialogForm{
    private final StaffBLL staffBLL = new StaffBLL();

    private DataTable dataTable;
    private RoundedPanel formDetail;
    private RoundedScrollPane scrollPaneFormDetail;
    private RoundedScrollPane scrollPaneDatatable;
    private List<JLabel> attributeStaff;
    private List<JTextField> jTextFieldStaff;
    private JButton buttonCancel;
    private JButton buttonAdd;
    private boolean flag;

    public FormAddStaffGUI() {
        super();
        super.setTitle("Thêm nhân viên");
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
        buttonAdd = new JButton("Thêm");
        scrollPaneDatatable = new RoundedScrollPane(containerTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneFormDetail = new RoundedScrollPane(formDetail, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        scrollPaneDatatable.setPreferredSize(new Dimension(600, 700));
        leftContent.add(scrollPaneDatatable, BorderLayout.CENTER);
        containerTable.add(dataTable);

        scrollPaneFormDetail.setPreferredSize(new Dimension(670, 610));
        rightContent.add(scrollPaneFormDetail, BorderLayout.NORTH);

        formDetail.setBackground(new Color(0xFFBDD2DB));
        formDetail.setLayout(new MigLayout("", "50[]20[]10", "20[]20[]"));

        for (String string : new String[]{"Mã nhân viên:", "Tên nhân viên:", "Giới tính:", "Ngày sinh:", "Số điện thoại:", "Địa chỉ:","Email:","Ngày vào làm:"}) {
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(170, 30));
            label.setText(string);
            label.setFont((new Font("FlatLaf.style", Font.PLAIN, 16)));
            attributeStaff.add(label);
            formDetail.add(label);

            JTextField textField = new JTextField();
            if (string.equals("Mã nhân viên:")) {
                textField.setText(String.valueOf(staffBLL.getAutoID(staffBLL.searchStaffs())));
                textField.setEnabled(false);
            }
            if (string.equals("Giới tính:")) {//click vào textbox thì đổi giữa Nam hoặc Nữ
                textField.setText("Nam");
                textField.setEnabled(false);
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
            }
            if(string.equals("Ngày sinh:")){
                addPlaceholder(textField, "yyyy-mm-dd");
            }
            if (string.equals("Thành viên:")) {//chưa làm để chọn combo box là Có hoặc Không
                textField.setText("Có");
                textField.setEnabled(false);
                textField.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(textField.getText().equals("Có")){
                            textField.setText("Không");
                        }
                        else{
                            textField.setText("Có");
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
            }
            if (string.equals("Ngày vào làm:")) {
//                textField.setText(String.valueOf(java.sql.Date.valueOf(LocalDate.now())));
                addPlaceholder(textField, "yyyy-mm-dd");
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

        buttonAdd.setPreferredSize(new Dimension(100,40));
        buttonAdd.setFont(new Font("FlatLaf.style", Font.BOLD, 15));
        buttonAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                addStaff();
            }
        });
        containerButton.add(buttonAdd);

    }
    private static void addPlaceholder(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }

    private void addStaff() {
        for (int i = 0; i < jTextFieldStaff.size(); i++) {
            if (i != 6 && jTextFieldStaff.get(i).getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin nhân viên.",
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
            int choice = JOptionPane.showOptionDialog(null, "Xác nhận thêm nhân viên?",
                "Thông báo", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (choice == 1) {
                if (staffBLL.addStaff(staff)) {
                    JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    StaffGUI.loadDataTable(staffBLL.getData());
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm nhân viên không thành công!",
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
        addPlaceholder(jTextFieldStaff.get(3),"yyyy-mm-dd");
        jTextFieldStaff.get(4).setText("");
        jTextFieldStaff.get(5).setText("");
        jTextFieldStaff.get(6).setText("");
        jTextFieldStaff.get(7).setText("");
        addPlaceholder(jTextFieldStaff.get(7),"yyyy-mm-dd");
        jTextFieldStaff.get(0).setEnabled(false);
        jTextFieldStaff.get(2).setEnabled(false);
    }

    public void selectRowTable() {
        String id = "";
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        int indexRow = dataTable.getSelectedRow();
        id = model.getDataVector().elementAt(indexRow).get(0).toString();
        if (flag) {
            jTextFieldStaff.get(3).setEnabled(true);
            jTextFieldStaff.get(3).setText(id);
            jTextFieldStaff.get(3).setEnabled(false);
        } else {
            jTextFieldStaff.get(5).setEnabled(true);
            jTextFieldStaff.get(5).setText(id);
            jTextFieldStaff.get(5).setEnabled(false);
        }
    }
}
