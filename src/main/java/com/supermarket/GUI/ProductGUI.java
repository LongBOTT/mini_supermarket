package com.supermarket.GUI;

import com.supermarket.BLL.ProductBLL;
import com.supermarket.GUI.components.DataTable;
import com.supermarket.GUI.components.Layout4;
import com.supermarket.GUI.components.RoundedPanel;
import com.supermarket.GUI.components.RoundedScrollPane;

import javax.swing.*;
import java.awt.*;

public class ProductGUI extends Layout4 {
    private ProductBLL productBLL = new ProductBLL();
    private DataTable dataTable;
    private RoundedScrollPane scrollPane;
    public ProductGUI(){
        super();
        initComponent();
//        JPanel jPanel = new JPanel();
//        jPanel.setBackground(new Color(4));
//        leftContent.add(jPanel, BorderLayout.CENTER);
    }

    public void initComponent(){
        dataTable = new DataTable(productBLL.getData(), new String[]{"Id sản phẩm","Tên sản phẩm","Mã nhãn hàng", "Mã loại", "Đơn vị", "Giá bán", "Số lượng", "barcode"}, e -> {});
        scrollPane = new RoundedScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leftContent.add(scrollPane, BorderLayout.CENTER);
    }
}
