package com.supermarket.GUI;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.supermarket.BLL.*;
import com.supermarket.DTO.*;
import com.supermarket.GUI.DialogGUI.FormAddProductGUI;
import com.supermarket.GUI.DialogGUI.FormDetailAccountGUI;
import com.supermarket.GUI.DialogGUI.FormUpdateAccountGUI;
import com.supermarket.GUI.DialogGUI.FormUpdateProductGUI;
import com.supermarket.GUI.components.DataTable;
import com.supermarket.GUI.components.Layout4;
import com.supermarket.GUI.components.RoundedScrollPane;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Objects;

public class ProductGUI extends Layout4 {
    private ProductBLL productBLL = new ProductBLL();
    private static DataTable dataTable;
    private RoundedScrollPane scrollPane;
    private JLabel iconDetail;
    private JLabel iconAdd;
    private JLabel iconEdit;
    private JLabel iconDelete;
    private Product product;
    private JComboBox cbbAttributeProduct;
    private JTextField jTextFieldSearch;
    private JComboBox cbbBrand;
    private JComboBox cbbCategory;
    private String id,name,brand_id,category_id,unit,cost,quantity,barcode,image;
    private BrandBLL brandBLL = new BrandBLL();
    private CategoryBLL categoryBLL = new CategoryBLL();
    private Object[][] productlist;
    public ProductGUI(){
        super();
        initComponent();
    }

    public void initComponent(){
        productlist = new Object[0][0];

        dataTable = new DataTable(new Object[][]{}, new String[]{"Id sản phẩm","Tên sản phẩm","Mã nhãn hàng", "Mã loại", "Đơn vị", "Giá bán", "Số lượng","Hình ảnh","barcode"}, e -> {});
        scrollPane = new RoundedScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leftContent.add(scrollPane, BorderLayout.CENTER);

        iconDetail = new JLabel();
        iconAdd = new JLabel();
        iconEdit = new JLabel();
        iconDelete = new JLabel();

        cbbAttributeProduct= new JComboBox(new String[] {"Tên sản phẩm","Mã nhãn hàng","Mã loại"});
        cbbBrand= new JComboBox<>();
        cbbCategory = new JComboBox<>();

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
        iconEdit.setIcon(new FlatSVGIcon("icon/edit.svg"));
        iconEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iconEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
                new FormUpdateProductGUI(productBLL.findProductsBy(Map.of("id", Integer.parseInt(model.getValueAt(dataTable.getSelectedRow(), 0).toString()))).get(0));
            }
        });
        leftMenu.add(iconEdit);

        iconDelete.setIcon(new FlatSVGIcon("icon/remove.svg"));
        iconDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iconDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                deleteProduct();
            }
        });
        leftMenu.add(iconDelete);

        cbbAttributeProduct.setPreferredSize(new Dimension(130, 30));
        cbbAttributeProduct.addActionListener(e -> selectSearchFilter());
        rightMenu.add(cbbAttributeProduct);

        for (Brand brand : brandBLL.getBrandList()) {
            cbbBrand.addItem(brand.getId());
        }
        rightMenu.add(cbbBrand);
        cbbBrand.setVisible(false);
        cbbBrand.addActionListener(e -> searchByBrand());

        for (Category category : categoryBLL.getCategoryList()) {
            cbbCategory.addItem(category.getId());
        }
        rightMenu.add(cbbCategory);
        cbbCategory.setVisible(false);
        cbbCategory.addActionListener(e -> searchByCategory());

        jTextFieldSearch = new JTextField();
        jTextFieldSearch.setPreferredSize(new Dimension(200, 30));
        jTextFieldSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchProduct();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchProduct();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchProduct();
            }
        });
        rightMenu.add(jTextFieldSearch);
        loadDataTable(productBLL.getData());
    }
    private void searchProduct() {
        if (jTextFieldSearch.getText().isEmpty()) {
            loadDataTable(productBLL.getData());
        } else {
            loadDataTable(productBLL.getData(productBLL.findProducts("name", jTextFieldSearch.getText())));
        }
    }

    private void searchByBrand() {
        for (Brand brand : brandBLL.getBrandList()) {
            if (brand.getName().equals(cbbBrand.getSelectedItem())) {
                loadDataTable(brandBLL.getData(brandBLL.findBrandsBy(Map.of("brand_id", brand.getId()))));
                return;
            }
        }
    }
    private void searchByCategory() {
        for (Category category : categoryBLL.getCategoryList()) {
            if (category.getName().equals(cbbCategory.getSelectedItem())) {
                System.out.println(cbbCategory.getSelectedItem());
                loadDataTable(categoryBLL.getData(categoryBLL.findCategoriesBy(Map.of("category_id", category.getId()))));
                return;
            }
        }
    }
    private void selectSearchFilter() {
        if (Objects.requireNonNull(cbbAttributeProduct.getSelectedItem()).toString().contains("Mã nhãn hàng")) {
            jTextFieldSearch.setVisible(false);
            cbbCategory.setVisible(false);
            cbbBrand.setSelectedIndex(0);
            cbbBrand.setVisible(true);
            searchByBrand();
        }else if (Objects.requireNonNull(cbbAttributeProduct.getSelectedItem()).toString().contains("Mã loại")) {
            jTextFieldSearch.setVisible(false);
            cbbBrand.setVisible(false);
            cbbCategory.setSelectedIndex(0);
            cbbCategory.setVisible(true);
            searchByCategory();
        } else {
            cbbCategory.setVisible(false);
            cbbBrand.setVisible(false);
            jTextFieldSearch.setVisible(true);
            searchProduct();
        }
    }
    private void deleteProduct() {
        if (dataTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xoá.",
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();

        String[] options = new String[]{"Huỷ", "Xác nhận"};
        int choice = JOptionPane.showOptionDialog(null, "Xác nhận xoá sản phẩm?",
            "Thông báo", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice == 1) {
            if (productBLL.deleteProduct(productBLL.findProductsBy(Map.of("id", Integer.parseInt(model.getValueAt(dataTable.getSelectedRow(), 0).toString()))).get(0))) {
                JOptionPane.showMessageDialog(null, "Xoá sản phẩm thành công!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                loadDataTable(productBLL.getData());
            } else {
                JOptionPane.showMessageDialog(null, "Xoá sản phẩm không thành công!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addProduct() {
        new FormAddProductGUI();
    }

    public static void loadDataTable(Object[][] objects) {
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        BrandBLL brandBLL = new BrandBLL();
        CategoryBLL categoryBLL = new CategoryBLL();
        for (Object[] object : objects) {
                int brandied = Integer.parseInt(object[2].toString());
                int categoryId = Integer.parseInt(object[3].toString());
                object[2] = String.valueOf(brandBLL.findBrandsBy(Map.of("id", brandied)).get(0).getId());
                object[3] = String.valueOf(categoryBLL.findCategoriesBy(Map.of("id", categoryId)).get(0).getId());

        }

        model.setRowCount(0);
        for (Object[] object : objects) {
            model.addRow(object);
        }
    }
}
