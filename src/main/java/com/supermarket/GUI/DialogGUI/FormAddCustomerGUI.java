package com.supermarket.GUI.DialogGUI;

import com.supermarket.BLL.CustomerBLL;
import com.supermarket.BLL.RoleBLL;
import com.supermarket.BLL.StaffBLL;
import com.supermarket.DTO.Customer;
import com.supermarket.DTO.Role;
import com.supermarket.DTO.Staff;
import com.supermarket.GUI.CustomerGUI;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FormAddCustomerGUI extends DialogForm{
    private final CustomerBLL customerBLL = new CustomerBLL();
    
    private DataTable dataTable;
    private RoundedPanel formDetail;
    private RoundedScrollPane scrollPaneFormDetail;
    private RoundedScrollPane scrollPaneDatatable;
    private List<JLabel> attributeCustomer;
    private List<JTextField> jTextFieldCustomer;
    private JButton buttonCancel;
    private JButton buttonAdd;
    private boolean flag;

    public FormAddCustomerGUI() {
        super();
        super.setTitle("Quản lý khách hàng");
        init();
        containerButton.setBackground(new Color(0xFFFFFF));
        setVisible(true);
    }

    public void init() {
        dataTable = new DataTable(new Object[][] {}, new String[] {}, e -> {});
        formDetail = new RoundedPanel();
        attributeCustomer = new ArrayList<>();
        jTextFieldCustomer = new ArrayList<>();
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

        for (String string : new String[]{"Mã khách hàng:", "Tên khách hàng:", "Giới tính:", "Ngày sinh:", "Số điện thoại:", "Thành viên:", "Lần đăng nhập cuối:", "Điểm thưởng:"}) {
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(170, 30));
            label.setText(string);
            label.setFont((new Font("FlatLaf.style", Font.PLAIN, 16)));
            attributeCustomer.add(label);
            formDetail.add(label);

            JTextField textField = new JTextField();
            if (string.equals("Mã khách hàng:")) {
                textField.setText(String.valueOf(customerBLL.getAutoID(customerBLL.searchCustomers())));
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
            if (string.equals("Lần đăng nhập cuối:")) {
                textField.setText(new Date().toString());
                textField.setEnabled(false);
            }
            if (string.equals("Điểm thưởng:")) {
                textField.setText("0");
                textField.setEnabled(false);
            }
            textField.setPreferredSize(new Dimension(400, 50));
            textField.setFont((new Font("FlatLaf.style", Font.BOLD, 14)));
            jTextFieldCustomer.add(textField);
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
                addCustomer();
            }
        });
        containerButton.add(buttonAdd);

    }

    private void addCustomer() {
        for (int i = 0; i < jTextFieldCustomer.size(); i++) {
            if (i != 6 && jTextFieldCustomer.get(i).getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin khách hàng.",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        try{
            int id = Integer.parseInt(jTextFieldCustomer.get(0).getText());
            String customerName = jTextFieldCustomer.get(1).getText();
            boolean gender = jTextFieldCustomer.get(2).getText().equals("Nam")?true:false;
            Date birthDate = Date.parseDate(jTextFieldCustomer.get(3).getText());
            String phoneNumber = jTextFieldCustomer.get(4).getText();
            boolean isMember = jTextFieldCustomer.get(5).getText().equals("Không")?false:true;
            Date lastSignedIn = Date.parseDate(jTextFieldCustomer.get(6).getText());
            int bonusPoint = Integer.parseInt(jTextFieldCustomer.get(7).getText());

            Customer customer = new Customer(id,customerName,gender,birthDate,phoneNumber,isMember,lastSignedIn,bonusPoint,false);

            String[] options = new String[]{"Huỷ", "Xác nhận"};
            int choice = JOptionPane.showOptionDialog(null, "Xác nhận thêm khách hàng?",
                "Thông báo", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (choice == 1) {
                if (customerBLL.addCustomer(customer)) {
                    JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    CustomerGUI.loadDataTable(customerBLL.getData());
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm khách hàng không thành công!",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void refresh() {
        jTextFieldCustomer.get(0).setEnabled(true);
        jTextFieldCustomer.get(0).setText(String.valueOf(customerBLL.getAutoID(customerBLL.searchCustomers())));
        jTextFieldCustomer.get(0).setEnabled(false);
        jTextFieldCustomer.get(1).setText("");
        jTextFieldCustomer.get(2).setText("");
        jTextFieldCustomer.get(3).setEnabled(true);
        jTextFieldCustomer.get(3).setText("");
        jTextFieldCustomer.get(3).setEnabled(false);
        jTextFieldCustomer.get(5).setEnabled(true);
        jTextFieldCustomer.get(5).setText("");
        jTextFieldCustomer.get(5).setEnabled(false);
    }

    public void selectRowTable() {
        String id = "";
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        int indexRow = dataTable.getSelectedRow();
        id = model.getDataVector().elementAt(indexRow).get(0).toString();
        if (flag) {
            jTextFieldCustomer.get(3).setEnabled(true);
            jTextFieldCustomer.get(3).setText(id);
            jTextFieldCustomer.get(3).setEnabled(false);
        } else {
            jTextFieldCustomer.get(5).setEnabled(true);
            jTextFieldCustomer.get(5).setText(id);
            jTextFieldCustomer.get(5).setEnabled(false);
        }
    }
}