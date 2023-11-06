package com.supermarket.GUI;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.components.FlatTableHeader;
import com.supermarket.BLL.BrandBLL;
import com.supermarket.BLL.CategoryBLL;
import com.supermarket.BLL.ProductBLL;
import com.supermarket.DTO.Brand;
import com.supermarket.DTO.Category;
import com.supermarket.DTO.Product;
import com.supermarket.GUI.components.DataTable;
import com.supermarket.GUI.components.RoundedPanel;
import com.supermarket.GUI.components.SalePanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SaleGUI extends SalePanel {
    private final ProductBLL productBLL = new ProductBLL();
    private final CategoryBLL categoryBLL = new CategoryBLL();
    private final BrandBLL brandBLL = new BrandBLL();
    private RoundedPanel containerProduct;
    private RoundedPanel containerProductDetail;
    private RoundedPanel containerBillDetail;
    private List<RoundedPanel> listProduct;
    private List<JLabel> productDetail;
    private List<JLabel> productImage;
    private JComboBox cbbAttributeProduct;
    private JComboBox cbbCategory;
    private JComboBox cbbBrand;
    private JTextField jtextFieldSearch;
    private List<JTextField> jtextFieldProductDetail;
    private JButton buttonAddProduct;
    private JButton buttonCancelBill_detail;
    private JButton buttonCancelBill;
    private JButton buttonConfirm;
    private JButton buttonPay;
    private DataTable cartShopping;
    private Object[][] products;
    private List<Integer> productsInCart;
    private int index;

    public SaleGUI() {
        super();
        initComponents();
    }

    public void initComponents() {
        containerProduct = new RoundedPanel();
        containerProductDetail = new RoundedPanel();
        containerBillDetail = new RoundedPanel();
        listProduct = new ArrayList<>();
        productDetail = new ArrayList<>();
        productImage = new ArrayList<>();
        cbbAttributeProduct = new JComboBox(new String[] {"Tên sản phẩm", "Thương hiệu", "Thể loại"});
        cbbCategory = new JComboBox();
        cbbBrand = new JComboBox();
        jtextFieldSearch = new JTextField();
        jtextFieldProductDetail = new ArrayList<>();
        buttonAddProduct = new JButton("Thêm");
        buttonCancelBill_detail = new JButton("Huỷ");
        buttonCancelBill = new JButton("Huỷ");
        buttonConfirm = new JButton("Xác nhận");
        buttonPay = new JButton("Thanh toán");
        products = new Object[0][0];
        productsInCart = new ArrayList<>();

        cbbAttributeProduct.setPreferredSize(new Dimension(130, 30));
        SearchPanel.add(cbbAttributeProduct);

        for (Category category : categoryBLL.getCategoryList()) {
            cbbCategory.addItem(category.getName());
        }

        for (Brand brand : brandBLL.getBrandList()) {
            cbbBrand.addItem(brand.getName());
        }

        jtextFieldSearch.setPreferredSize(new Dimension(200, 30));

        SearchPanel.add(jtextFieldSearch);

        containerProduct.setLayout(new MigLayout("", "10[]20", "[]10[]"));
        containerProduct.setBackground(new Color(0xFFFFFF));
        containerProduct.setAutoscrolls(true);
        ProductPanel.add(containerProduct, BorderLayout.CENTER);
        loadProduct(productBLL.getProductList());

        containerProductDetail.setLayout(new MigLayout("", "10[][]10", "10[]20[]"));
        containerProductDetail.setBackground(new Color(0xFFFFFF));
        containerProductDetail.setAutoscrolls(true);
        Product_detailPanel.add(containerProductDetail);
        for (String string : new String[]{"Tên sản phẩm:", "Thương hiệu:", "Thể loại:", "Đơn giá:", "Mã vạch:", "Số lượng:"}) {
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(170, 30));
            label.setText(string);
            label.setFont((new Font("FlatLaf.style", Font.PLAIN, 16)));
            productDetail.add(label);
            containerProductDetail.add(label);

            JTextField textField = new JTextField();
            if (string.equals("Số lượng:")) {
                textField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                            e.consume();
                        }
                    }
                });

            } else {
                textField.setEditable(false);
            }
            textField.setPreferredSize(new Dimension(200, 50));
            textField.setFont((new Font("FlatLaf.style", Font.PLAIN, 15)));
            jtextFieldProductDetail.add(textField);
            containerProductDetail.add(textField, "wrap");
        }

        containerProductDetail.add(new JLabel());
        buttonAddProduct.setPreferredSize(new Dimension(40,40));
        buttonAddProduct.setFont(new Font("FlatLaf.style", Font.BOLD, 15));
        buttonAddProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                addProductToCart();
            }
        });
        containerProductDetail.add(buttonAddProduct);

        containerBillDetail.setLayout(new BorderLayout());
        containerBillDetail.setBackground(new Color(0x93D3D3D3, true));
        Bill_detailPanel.add(containerBillDetail, BorderLayout.CENTER);

        cartShopping = new DataTable(new Object[][] {},
            new String[]{"Tên sản phẩm", "Thương hiệu", "Thể loại", "Số lượng", "Giá tiền"},
            e -> {});
        containerBillDetail.add(cartShopping.getTableHeader(),BorderLayout.NORTH);
        containerBillDetail.add(cartShopping);

        buttonCancelBill_detail.setFont(new Font("FlatLaf.style", Font.BOLD, 15));
        buttonCancelBill_detail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonCancelBill_detail.setBackground(new Color(0xFC0202));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonCancelBill_detail.setBackground(null);
            }
        });
        ContainerButtonsBill_detail.add(buttonCancelBill_detail);

        buttonConfirm.setFont(new Font("FlatLaf.style", Font.BOLD, 15));
        ContainerButtonsBill_detail.add(buttonConfirm);
    }

    private void addProductToCart() {
        if (productsInCart.contains(index)) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã được thêm vào giỏ hàng.",
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Object[] objects = new Object[5];
        for (int i = 0; i < 3; i++) {
            objects[i] = jtextFieldProductDetail.get(i).getText();
        }
        objects[3] = jtextFieldProductDetail.get(5).getText();
        objects[4] = Double.parseDouble(jtextFieldProductDetail.get(5).getText()) * Double.parseDouble(jtextFieldProductDetail.get(3).getText());
        products = Arrays.copyOf(products, products.length + 1);
        products[products.length - 1] = objects;
        productsInCart.add(index);
        loadCartShopping(products);
    }

    public void loadProduct (List<Product> products) {
        containerProduct.removeAll();
        listProduct = new ArrayList<>();
        productImage = new ArrayList<>();
        for (Product product : productBLL.getProductList()) {
            RoundedPanel panel = new RoundedPanel();
            panel.setLayout(new BorderLayout());
            panel.setBackground(new Color(0x93D3D3D3, true));
            panel.setPreferredSize(new Dimension(360, 130));
            listProduct.add(panel);

            JLabel label = new JLabel();
            label.setIcon(new FlatSVGIcon(product.getImage()));
            label.setFont((new Font("FlatLaf.style", Font.PLAIN, 15)));
            label.setIconTextGap(30);
            label.setText("<html>" + product.getName() + "<html>");
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            productImage.add(label);
            panel.add(label);
        }

        for (JLabel label : productImage) {
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    for (int i = 0;  i < productImage.size()-1; i++) {
                        if (productImage.get(i) == e.getComponent()) {
                            index = i;
                            loadProductDetail(productBLL.getProductList().get(i));
                        }
                    }
                }
            });
        }
        for (RoundedPanel panel : listProduct) {
            containerProduct.add(panel, "wrap");
        }
        containerProduct.repaint();
        containerProduct.revalidate();
        ProductPanel.add(containerProduct);
    }

    public void loadProductDetail(Product product) {
        jtextFieldProductDetail.get(0).setText(product.getName());
        jtextFieldProductDetail.get(1).setText(brandBLL.findBrandsBy(Map.of("id", product.getBrand_id())).get(0).getName());
        jtextFieldProductDetail.get(2).setText(categoryBLL.findCategoriesBy(Map.of("id", product.getCategory_id())).get(0).getName());
        jtextFieldProductDetail.get(3).setText(String.valueOf(product.getCost()));
        jtextFieldProductDetail.get(4).setEditable(true);
        jtextFieldProductDetail.get(4).setText(null);
        jtextFieldProductDetail.get(4).setEditable(false);
    }

    public void loadCartShopping(Object[][] objects) {
        DefaultTableModel model = (DefaultTableModel) cartShopping.getModel();
        model.setRowCount(0);
        for (Object[] object : objects) {
            model.addRow(object);
        }
    }
}
