package com.supermarket.GUI;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.supermarket.BLL.ProductBLL;
import com.supermarket.BLL.RoleBLL;
import com.supermarket.BLL.StaffBLL;
import com.supermarket.DTO.Product;
import com.supermarket.GUI.DialogGUI.FormAddProductGUI;
import com.supermarket.GUI.components.DataTable;
import com.supermarket.GUI.components.Layout4;
import com.supermarket.GUI.components.RoundedScrollPane;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class ProductGUI extends Layout4 {
    private ProductBLL productBLL = new ProductBLL();
    private static DataTable dataTable;
    private RoundedScrollPane scrollPane;
    private JLabel iconDetail;
    private JLabel iconAdd;
    private JLabel iconEdit;
    private JLabel iconDelete;
    private Product product;
    private String id,name,brand_id,category_id,unit,cost,quantity,barcode;
    public ProductGUI(){
        super();
        initComponent();
    }

    public void initComponent(){
        dataTable = new DataTable(productBLL.getData(), new String[]{"Id sản phẩm","Tên sản phẩm","Mã nhãn hàng", "Mã loại", "Đơn vị", "Giá bán", "Số lượng", "barcode"}, e -> {});
        scrollPane = new RoundedScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leftContent.add(scrollPane, BorderLayout.CENTER);

        iconDetail = new JLabel();
        iconAdd = new JLabel();
        iconEdit = new JLabel();
        iconDelete = new JLabel();

        ListSelectionModel selectionModel = dataTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener((new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    rightContent.removeAll();
                    rightContent.revalidate();
                    rightContent.repaint();

                    rightContent.setLayout(new FlowLayout());
                    int selectedRow = dataTable.getSelectedRow();
                    if (selectedRow != -1) {
                        id = dataTable.getValueAt(selectedRow,0).toString();
                        name = dataTable.getValueAt(selectedRow,1).toString();
                        brand_id = dataTable.getValueAt(selectedRow,2).toString();
                        category_id = dataTable.getValueAt(selectedRow,3).toString();
                        unit = dataTable.getValueAt(selectedRow,4).toString();
                        cost = dataTable.getValueAt(selectedRow,5).toString();
                        quantity = dataTable.getValueAt(selectedRow,6).toString();
                    }
                    for (String string : new String[]{"Id sản phẩm:", "Tên sản phẩm:", "Mã nhãn hàng:", "Mã loại:", "Đơn vị:","Giá bán:","Số lượng:"}) {
                        JLabel label = new JLabel();
                        label.setPreferredSize(new Dimension(130, 30));
                        label.setText(string);
                        label.setFont((new Font("FlatLaf.style", Font.PLAIN, 16)));
                        rightContent.add(label);

                        JTextField textField= new JTextField();
                        if (string.equals("Id sản phẩm:")) {
                            textField.setText(String.valueOf(id));
                        }
                        if (string.equals("Tên sản phẩm:")) {
                            textField.setText(String.valueOf(name));
                        }
                        if (string.equals("Mã nhãn hàng:")) {
                            textField.setText(String.valueOf(brand_id));
                        }
                        if (string.equals("Mã loại:")) {
                            textField.setText(String.valueOf(category_id));
                        }
                        if (string.equals("Đơn vị:")) {
                            textField.setText(String.valueOf(unit));
                        }
                        if (string.equals("Giá bán:")) {
                            textField.setText(String.valueOf(cost));
                        }
                        if (string.equals("Số lượng:")) {
                            textField.setText(String.valueOf(quantity));
                        }
                        /**if (string.equals("Barcode")) {
                            textField.setText(String.valueOf(barcode));
                        }*/
                        textField.setPreferredSize(new Dimension(170,30));
                        textField.setFont((new Font("FlatLaf.style", Font.PLAIN, 14)));
                        rightContent.add(textField);

                        iconEdit.setIcon(new FlatSVGIcon("icon/edit.svg"));
                        iconEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        iconEdit.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                updateProduct();
                            }
                        });
                        rightContent.add(iconEdit);

                        iconDelete.setIcon(new FlatSVGIcon("icon/remove.svg"));
                        iconDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        iconDelete.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                deleteProduct();
                            }
                        });
                        rightContent.add(iconDelete);
                    }
                }
            }
        }));

        iconAdd.setIcon(new FlatSVGIcon("icon/add.svg"));
        iconAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iconAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                addProduct();
            }
        });
        leftMenu.add(iconAdd);

        /**iconDetail.setIcon(new FlatSVGIcon("icon/detail.svg"));
        iconDetail.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iconDetail.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showDetailProduct();
            }
        });
        leftMenu.add(iconDetail);*/



    }

    private void deleteProduct() {

    }

    private void updateProduct() {

    }

    private void addProduct() {
        new FormAddProductGUI();
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
