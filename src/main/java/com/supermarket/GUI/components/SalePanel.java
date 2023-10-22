package com.supermarket.GUI.components;

import javax.swing.*;
import java.awt.*;

public class SalePanel extends RoundedPanel{
    public RoundedPanel left;
    public RoundedPanel right;
    public RoundedPanel top;
    public RoundedPanel bottom;
    public RoundedPanel ProductPanel;
    public RoundedPanel Product_detailPanel;
    public RoundedPanel Bill_detailPanel;
    public RoundedPanel BillPanel;
    public RoundedScrollPane[] scrollPane;
    public SalePanel() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setBackground(new Color(0xFFFFFF));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1140, 760));


        left = new RoundedPanel();
        right = new RoundedPanel();
        top = new RoundedPanel();
        bottom = new RoundedPanel();
        ProductPanel = new RoundedPanel();
        Product_detailPanel = new RoundedPanel();
        Bill_detailPanel = new RoundedPanel();
        BillPanel = new RoundedPanel();
        scrollPane = new RoundedScrollPane[3];
        scrollPane[0] = new RoundedScrollPane(ProductPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane[1] = new RoundedScrollPane(Product_detailPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane[2] = new RoundedScrollPane(Bill_detailPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        for (RoundedScrollPane roundedScrollPane: scrollPane) {
            roundedScrollPane.getVerticalScrollBar().setUnitIncrement(15);
        }

        left.setLayout(new BorderLayout());
        left.setPreferredSize(new Dimension(800, 760));
        left.setBackground(new Color(0xFFFFFF));
        add(left, BorderLayout.WEST);

        right.setLayout(new BorderLayout());
        right.setPreferredSize(new Dimension(350, 760));
        add(right, BorderLayout.EAST);

        top.setLayout(new BorderLayout());
        top.setPreferredSize(new Dimension(800, 500));
        top.setBackground(new Color(0xFFFFFF));
        left.add(top, BorderLayout.NORTH);

        bottom.setLayout(new BorderLayout());
        bottom.setPreferredSize(new Dimension(800, 250));
        bottom.setBackground(new Color(0xFFFFFF));
        left.add(bottom, BorderLayout.SOUTH);

        Bill_detailPanel.setLayout(new BorderLayout());
        Bill_detailPanel.setPreferredSize(new Dimension(800, 500));
        Bill_detailPanel.setBackground(new Color(0x8D302C));
        bottom.add(scrollPane[2], BorderLayout.CENTER);

        ProductPanel.setLayout(new BorderLayout());
        ProductPanel.setPreferredSize(new Dimension(395, 800));
        ProductPanel.setBackground(new Color(0xF39BF2));
        top.add(scrollPane[0], BorderLayout.WEST);

        Product_detailPanel.setLayout(new BorderLayout());
        Product_detailPanel.setPreferredSize(new Dimension(395, 800));
        Product_detailPanel.setBackground(new Color(0xAB870E));
        top.add(scrollPane[1], BorderLayout.EAST);

        BillPanel.setLayout(new BorderLayout());
        BillPanel.setPreferredSize(new Dimension(350, 760));
        BillPanel.setBackground(new Color(0x346077));
        right.add(BillPanel, BorderLayout.CENTER);

//        RoundedPanel panel = new RoundedPanel();
//        panel.setLayout(new BorderLayout());
//        panel.setPreferredSize(new Dimension(800, 800));
//        panel.setBackground(new Color(0xC6F39B));
//        Product_detailPanel.add(panel);

    }

    public static void main(String[] args) {
        new SalePanel();
    }
}
