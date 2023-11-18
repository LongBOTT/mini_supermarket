package com.supermarket.GUI.DialogGUI;

import com.supermarket.BLL.ProductBLL;
import com.supermarket.BLL.CategoryBLL;
import com.supermarket.BLL.BrandBLL;
import com.supermarket.DTO.Product;
import com.supermarket.DTO.Brand;
import com.supermarket.DTO.Category;
import com.supermarket.GUI.ProductGUI;
import com.supermarket.GUI.components.DataTable;
import com.supermarket.GUI.components.RoundedPanel;
import com.supermarket.GUI.components.RoundedScrollPane;
import com.supermarket.utils.DateTime;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FormAddProductGUI extends DialogForm{
    private final ProductBLL productBLL = new ProductBLL();
    private final BrandBLL brandBLL = new BrandBLL();
    private final CategoryBLL categoryBLL = new CategoryBLL();
    private DataTable dataTable;
    private RoundedPanel formDetail;
    private RoundedScrollPane scrollPaneFormDetail;
    private RoundedScrollPane scrollPaneDatatable;
    private List<JLabel> attributeAccount;
    private List<JTextField> jTextFieldProduct;
    private JButton buttonCancel;
    private JButton buttonAdd;
    private boolean flag;

    public FormAddProductGUI() {
        super();
        super.setTitle("Quản lý sản phẩm");
        init();
        containerButton.setBackground(new Color(0xFFFFFF));
        setVisible(true);
    }

    public void init() {
        dataTable = new DataTable(new Object[][] {}, new String[] {}, e -> {});
        formDetail = new RoundedPanel();
        attributeAccount = new ArrayList<>();
        jTextFieldProduct = new ArrayList<>();
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

        for (String string : new String[]{"Id sản phẩm:", "Tên sản phẩm:", "Mã nhãn hàng:", "Mã loại:", "Đơn vị:","Giá bán:","Số lượng:"}) {
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(170, 30));
            label.setText(string);
            label.setFont((new Font("FlatLaf.style", Font.PLAIN, 16)));
            attributeAccount.add(label);
            formDetail.add(label);

            JTextField textField = new JTextField();
            if (string.equals("Id sản phẩm:")) {
                textField.setText(String.valueOf(productBLL.getAutoID(productBLL.searchProducts())));
                textField.setEnabled(false);
            }
            textField.setPreferredSize(new Dimension(400, 50));
            textField.setFont((new Font("FlatLaf.style", Font.BOLD, 14)));
            jTextFieldProduct.add(textField);
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
                addProduct();
            }
        });
        containerButton.add(buttonAdd);

    }

    private void addProduct() {
        for (int i = 0; i < jTextFieldProduct.size(); i++) {
            if (i != 4 && jTextFieldProduct.get(i).getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin tài khoản.",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        int id = Integer.parseInt(jTextFieldProduct.get(0).getText());
        String name = jTextFieldProduct.get(1).getText();
        int brandID = Integer.parseInt(jTextFieldProduct.get(2).getText());
        int categoryID = Integer.parseInt(jTextFieldProduct.get(3).getText());
        String unit = jTextFieldProduct.get(4).getText();
        double cost = Double.parseDouble((jTextFieldProduct.get(5).getText()));
        double quantity = Double.parseDouble((jTextFieldProduct.get(6).getText()));

        Product product = new Product(id, name,brandID,categoryID,unit,cost,quantity,"","",false);

        String[] options = new String[]{"Huỷ", "Xác nhận"};
        int choice = JOptionPane.showOptionDialog(null, "Xác nhận thêm tài khoản?",
            "Thông báo", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice == 1) {
            if (productBLL.addProduct(product)) {
                JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                ProductGUI.loadDataTable(productBLL.getData());
                refresh();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm tài khoản không thành công!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void refresh() {
        jTextFieldProduct.get(0).setEnabled(true);
        jTextFieldProduct.get(0).setText(String.valueOf(productBLL.getAutoID(productBLL.searchProducts())));
        jTextFieldProduct.get(0).setEnabled(false);
        jTextFieldProduct.get(1).setText("");
        jTextFieldProduct.get(2).setText("");
        jTextFieldProduct.get(3).setEnabled(true);
        jTextFieldProduct.get(3).setText("");
        jTextFieldProduct.get(3).setEnabled(false);
        jTextFieldProduct.get(5).setEnabled(true);
        jTextFieldProduct.get(5).setText("");
        jTextFieldProduct.get(5).setEnabled(false);
    }

    /*private void loadTableCategory() {
        flag = false;
        dataTable = new DataTable(categoryBLL.getData(), new String[] {"Id", "Họ tên", "Số lượng"}, e -> selectRowTable());
        dataTable.setPreferredSize(new Dimension(1500, 700));
        containerTable.removeAll();
        containerTable.add(dataTable.getTableHeader(), BorderLayout.NORTH);
        containerTable.add(dataTable, BorderLayout.CENTER);

        if (!jTextFieldProduct.get(3).getText().isEmpty()) {
            Category category = categoryBLL.findCategoriesBy(Map.of("id", Integer.parseInt(jTextFieldProduct.get(5).getText()))).get(0);
            int index = staffBLL.getIndex(staff, "id", staffBLL.getStaffList());
            dataTable.setRowSelectionInterval(index, index);
        }

        containerTable.repaint();
        containerTable.revalidate();
        scrollPaneDatatable.setViewportView(containerTable);
    }

    private void loadTableRole() {
        flag = true;
        dataTable = new DataTable(roleBLL.getData(), new String[] {"Mã chức vụ", "Tên chức vụ"}, e -> selectRowTable());
        containerTable.removeAll();
        containerTable.add(dataTable.getTableHeader(), BorderLayout.NORTH);
        containerTable.add(dataTable, BorderLayout.CENTER);

        if (!jTextFieldAccount.get(3).getText().isEmpty()) {
            Role role = roleBLL.findRolesBy(Map.of("id", Integer.parseInt(jTextFieldAccount.get(3).getText()))).get(0);
            int index = roleBLL.getIndex(role, "id", roleBLL.getRoleList());
            dataTable.setRowSelectionInterval(index, index);
        }


        containerTable.repaint();
        containerTable.revalidate();
        scrollPaneDatatable.setViewportView(containerTable);
    }

    public void selectRowTable() {
        String id = "";
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        int indexRow = dataTable.getSelectedRow();
        id = model.getDataVector().elementAt(indexRow).get(0).toString();
        if (flag) {
            jTextFieldAccount.get(3).setEnabled(true);
            jTextFieldAccount.get(3).setText(id);
            jTextFieldAccount.get(3).setEnabled(false);
        } else {
            jTextFieldAccount.get(5).setEnabled(true);
            jTextFieldAccount.get(5).setText(id);
            jTextFieldAccount.get(5).setEnabled(false);
        }
    }*/
}
