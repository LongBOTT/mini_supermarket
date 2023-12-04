package com.supermarket.GUI;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.supermarket.BLL.*;
import com.supermarket.DTO.*;
import com.supermarket.GUI.DialogGUI.*;
import com.supermarket.GUI.components.DataTable;
import com.supermarket.GUI.components.Layout4;
import com.supermarket.GUI.components.RoundedPanel;
import com.supermarket.GUI.components.RoundedScrollPane;
import com.toedter.calendar.JDateChooser;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class ProductGUI extends Layout4 {
    private ProductBLL productBLL = new ProductBLL();
    private static DataTable dataTable;
    private RoundedScrollPane scrollPane;
    private JLabel iconDetail;
    private JLabel iconAdd;
    private JLabel iconEdit;
    private JLabel iconDelete;
    private RoundedPanel formDetail;
    private Product product;
    private JComboBox cbbAttributeProduct;
    private JTextField jTextFieldSearch;
    private List<JTextField> jTextFieldBrand;
    private List<JTextField> jTextFieldCategory;
    private JComboBox cbbBrand;
    private JComboBox cbbCategory;
    private BrandBLL brandBLL = new BrandBLL();
    private CategoryBLL categoryBLL = new CategoryBLL();
    private static Object[][] productlist;
    public ProductGUI(List<Function> functions) {
        super();
        initComponent(functions);
    }

    public void initComponent(List<Function> functions) {
        productlist = new Object[0][0];

        formDetail = new RoundedPanel();
        dataTable = new DataTable(new Object[][]{}, new String[]{"Mã sản phẩm","Tên sản phẩm","Thương hiệu", "Thể loại", "Giá bán", "Số lượng"}, e -> {});
        scrollPane = new RoundedScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leftContent.add(scrollPane, BorderLayout.CENTER);

        iconDetail = new JLabel();
        iconAdd = new JLabel();
        iconEdit = new JLabel();
        iconDelete = new JLabel();
        jTextFieldCategory = new ArrayList<>();
        jTextFieldBrand = new ArrayList<>();

        formDetail.setBackground(new Color(0xFFBDD2DB));
        formDetail.setLayout(new MigLayout("", "50[]20[]10", "20[]20[]"));

        cbbAttributeProduct= new JComboBox(new String[] {"Tên sản phẩm","Thương hiệu","Thể loại", "Sắp hết hàng"});
        cbbBrand= new JComboBox<>();
        cbbCategory = new JComboBox<>();

        if (functions.stream().anyMatch(f -> f.getName().equals("Chi tiết"))) {
            iconDetail.setIcon(new FlatSVGIcon("icon/detail.svg"));
            iconDetail.setCursor(new Cursor(Cursor.HAND_CURSOR));
            iconDetail.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    showDetailAccount();
                }
            });
            leftMenu.add(iconDetail);
        }

        if (functions.stream().anyMatch(f -> f.getName().equals("Thêm"))) {
            iconAdd.setIcon(new FlatSVGIcon("icon/add.svg"));
            iconAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
            iconAdd.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    addProduct();
                }
            });
            leftMenu.add(iconAdd);
        }

        if (functions.stream().anyMatch(f -> f.getName().equals("Sửa"))) {
            iconEdit.setIcon(new FlatSVGIcon("icon/edit.svg"));
            iconEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            iconEdit.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    updateProduct();
                }
            });
            leftMenu.add(iconEdit);
        }

        if (functions.stream().anyMatch(f -> f.getName().equals("Xóa"))) {
            iconDelete.setIcon(new FlatSVGIcon("icon/remove.svg"));
            iconDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
            iconDelete.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    deleteProduct();
                }
            });
            leftMenu.add(iconDelete);
        }

        cbbAttributeProduct.setPreferredSize(new Dimension(130, 30));
        cbbAttributeProduct.addActionListener(e -> selectSearchFilter());
        rightMenu.add(cbbAttributeProduct);

        for (Brand brand : brandBLL.getBrandList()) {
            cbbBrand.addItem(brand.getName());
        }
        rightMenu.add(cbbBrand);
        cbbBrand.setVisible(false);
        cbbBrand.addActionListener(e -> searchByBrand());

        for (Category category : categoryBLL.getCategoryList()) {
            cbbCategory.addItem(category.getName());
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
        loadDataTable(productBLL.getProductList());
    }

    private void showDetailAccount() {
        productBLL = new ProductBLL();
        if (dataTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xem chi tiết.",
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        new FormDetailProductGUI(productBLL.findProductsBy(Map.of("id", Integer.parseInt(model.getValueAt(dataTable.getSelectedRow(), 0).toString()))).get(0));
    }

    private void searchProduct() {
        if (jTextFieldSearch.getText().isEmpty()) {
            loadDataTable(productBLL.getProductList());
        } else {
            loadDataTable(productBLL.findProducts("name", jTextFieldSearch.getText()));
        }
    }

    private void searchByBrand() {
        bottom.removeAll();
        formDetail.removeAll();
        JLabel title = new JLabel("THƯƠNG HIỆU");
        title.setFont((new Font("FlatLaf.style", Font.BOLD, 23)));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        formDetail.add(title, "span 2 1, wrap");

        SupplierBLL supplierBLL = new SupplierBLL();
        Brand brand1 = brandBLL.getBrandList().get(cbbBrand.getSelectedIndex());
        Supplier supplier = supplierBLL.findSuppliersBy(Map.of("id", brand1.getSupplier_id())).get(0);
        for (String string : new String[] {"Tên thương hiệu:", "Nhà cung cấp:"}) {
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(170, 30));
            label.setText(string);
            label.setFont((new Font("FlatLaf.style", Font.PLAIN, 14)));
            formDetail.add(label, "wrap");

            JTextField textField = new JTextField();
            if (string.equals("Tên thương hiệu:")) {
                textField.setText(brand1.getName());
            }
            if (string.equals("Nhà cung cấp:")) {
                textField.setText(supplier.getName());
            }
            textField.setPreferredSize(new Dimension(400, 50));
            textField.setFont((new Font("FlatLaf.style", Font.PLAIN, 14)));
            jTextFieldBrand.add(textField);
            formDetail.add(textField, "wrap");
        }

        rightContent.add(formDetail);
        rightContent.repaint();
        rightContent.revalidate();
        bottom.add(leftContent, BorderLayout.WEST);
        bottom.add(rightContent, BorderLayout.EAST);
        bottom.repaint();
        bottom.revalidate();

        for (Brand brand : brandBLL.getBrandList()) {
            if (brand.getName().equals(cbbBrand.getSelectedItem())) {
                loadDataTable(productBLL.findProductsBy(Map.of("brand_id", brand.getId())));
                return;
            }
        }
    }

    private void searchByCategory() {
        bottom.removeAll();
        formDetail.removeAll();
        JLabel title = new JLabel("THỂ LOẠI");
        title.setFont((new Font("FlatLaf.style", Font.BOLD, 23)));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        formDetail.add(title, "span 2 1, wrap");

        Category category1 = categoryBLL.getCategoryList().get(cbbCategory.getSelectedIndex());
        for (String string : new String[] {"Tên thể loại:"}) {
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(170, 30));
            label.setText(string);
            label.setFont((new Font("FlatLaf.style", Font.PLAIN, 14)));
            formDetail.add(label, "wrap");

            JTextField textField = new JTextField();
            textField.setText(category1.getName());

            textField.setPreferredSize(new Dimension(400, 50));
            textField.setFont((new Font("FlatLaf.style", Font.PLAIN, 14)));
            jTextFieldBrand.add(textField);
            formDetail.add(textField, "wrap");
        }

        rightContent.add(formDetail);
        rightContent.repaint();
        rightContent.revalidate();
        bottom.add(leftContent, BorderLayout.WEST);
        bottom.add(rightContent, BorderLayout.EAST);
        bottom.repaint();
        bottom.revalidate();
        for (Category category : categoryBLL.getCategoryList()) {
            if (category.getName().equals(cbbCategory.getSelectedItem())) {
                loadDataTable(productBLL.findProductsBy(Map.of("category_id", category.getId())));
                return;
            }
        }
    }
    private void selectSearchFilter() {
        if (Objects.requireNonNull(cbbAttributeProduct.getSelectedItem()).toString().contains("Thương hiệu")) {
            jTextFieldSearch.setVisible(false);
            cbbCategory.setVisible(false);
            cbbBrand.setSelectedIndex(0);
            cbbBrand.setVisible(true);
            searchByBrand();
            return;
        } else if (Objects.requireNonNull(cbbAttributeProduct.getSelectedItem()).toString().contains("Thể loại")) {
            jTextFieldSearch.setVisible(false);
            cbbBrand.setVisible(false);
            cbbCategory.setSelectedIndex(0);
            cbbCategory.setVisible(true);
            searchByCategory();
            return;
        } else if (Objects.requireNonNull(cbbAttributeProduct.getSelectedItem()).toString().contains("Sắp hết hàng")) {
            jTextFieldSearch.setVisible(false);
            cbbBrand.setVisible(false);
            cbbCategory.setVisible(false);
            searchByQuantity();
        } else {
            cbbCategory.setVisible(false);
            cbbBrand.setVisible(false);
            jTextFieldSearch.setVisible(true);
            searchProduct();
        }
        bottom.removeAll();
        bottom.add(leftContent, BorderLayout.CENTER);
        bottom.repaint();
        bottom.revalidate();
    }

    private void searchByQuantity() {
        loadDataTable(productBLL.searchProducts("deleted = 0", "`product`.quantity > 0 AND `product`.quantity <= 20"));
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

                loadDataTable(productBLL.getProductList());
            } else {
                JOptionPane.showMessageDialog(null, "Xoá sản phẩm không thành công!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addProduct() {
        new FormAddProductGUI();
    }

    private void updateProduct() {
        productBLL = new ProductBLL();
        if (dataTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần cập nhật.",
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        new FormUpdateProductGUI(productBLL.findProductsBy(Map.of("id", Integer.parseInt(model.getValueAt(dataTable.getSelectedRow(), 0).toString()))).get(0));
    }

    public static  void loadDataTable(List<Product> products) {
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        productlist = new Object[0][0];
        BrandBLL brandBLL = new BrandBLL();
        CategoryBLL categoryBLL = new CategoryBLL();
        for (Product product : products) {
            Object[] object = new Object[6];
            object[0] = product.getId();
            object[1] = product.getName();
            object[2] = brandBLL.findBrandsBy(Map.of("id", product.getBrand_id())).get(0).getName();
            object[3] = categoryBLL.findCategoriesBy(Map.of("id", product.getCategory_id())).get(0).getName();
            object[4] = product.getCost();
            object[5] = product.getQuantity();
            productlist = Arrays.copyOf(productlist, productlist.length + 1);
            productlist[productlist.length - 1] = object;
        }

        model.setRowCount(0);
        for (Object[] object : productlist) {
            model.addRow(object);
        }
    }
}
