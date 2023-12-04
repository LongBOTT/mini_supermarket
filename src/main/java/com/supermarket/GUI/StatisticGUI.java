package com.supermarket.GUI;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.sun.javafx.application.PlatformImpl;
import com.supermarket.BLL.ProductBLL;
import com.supermarket.BLL.StaffBLL;
import com.supermarket.DAL.MySQL;
import com.supermarket.DTO.Product;
import com.supermarket.DTO.Staff;
import com.supermarket.GUI.components.*;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatisticGUI extends StatisticPanel {
    private List<JLabel> listTab;
    private int currentTab = -1;
    private RoundedPanel genneralPanel;
    private RoundedPanel statisticByYearPanel;
    private RoundedPanel statisticByQuarterPanel;
    private RoundedPanel statisticByMonthPanel;
    public StatisticGUI() {
        super();
        PlatformImpl.startup(() -> {});
        init();
        selectedTab(0);
    }

    private void init() {
        listTab = new ArrayList<>();
        genneralPanel = new RoundedPanel();
        statisticByYearPanel = new RoundedPanel();
        statisticByQuarterPanel = new RoundedPanel();
        statisticByMonthPanel = new RoundedPanel();

        int index = 0;
        for (String string : new String[]{"Tổng quát", "Theo năm", "Theo quý", "Theo quý tháng"}) {
            JLabel label = new JLabel();
            label.setText(string);
            listTab.add(label);
            tabs[index].add(label);
            tabs[index].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    for (int i = 0; i < tabs.length; i++) {
                        if (tabs[i] == e.getComponent())
                            selectedTab(i);
                    }
                }
            });
            index++;
        }
        genneralPanel = genneral();
        statisticByYear();

    }

    private void selectedTab(int index) {
        if (currentTab != index) {
            active(index);
            JPanel panel = switch (index) {
                case 0 -> genneralPanel;
                case 1 -> statisticByYearPanel;
                case 2 -> statisticByQuarter();
                case 3 -> statisticByMonth();
                default -> null;
            };
            currentTab = index;
            OpenTab(panel);
        }
    }

    private void active(int index) {
        tabs[index].setBackground(new Color(0xFFBDD2DB, true));
        for (int i  = 0; i < tabs.length; i++) {
            if (i != index) {
                tabs[i].setBackground(new Color(0xA8A8AF));
            }
        }
    }

    private void OpenTab(JPanel panel) {
        content.removeAll();
        content.add(panel, BorderLayout.CENTER);
        content.repaint();
        content.revalidate();
        add(content, BorderLayout.CENTER);
    }

    private RoundedPanel genneral() {
        ProductBLL productBLL = new ProductBLL();
        StaffBLL staffBLL = new StaffBLL();
        RoundedPanel genneralTab = new RoundedPanel();
        List<RoundedPanel> listItem = new ArrayList<>();
        List<JLabel> listIcon = new ArrayList<>();
        List<JLabel> listValue = new ArrayList<>();
        List<JLabel> listTitle = new ArrayList<>();
        genneralTab.setLayout(new MigLayout("", "10[]15[]10", "10[]20[]"));
        genneralTab.setPreferredSize(new Dimension(1140, 760));
        genneralTab.setBackground(new Color(0xFFBDD2DB, true));

        for (int i = 0; i < 12; i++) {
            RoundedPanel roundedPanel = new RoundedPanel();
            roundedPanel.setLayout(new MigLayout());
            roundedPanel.setPreferredSize(new Dimension(274, 225));
            roundedPanel.setBackground(new Color(0x80B27F7F, true));
            listItem.add(roundedPanel);

            JLabel label = new JLabel();
            listIcon.add(label);

            JLabel label1 = new JLabel();
            label1.setFont(new Font("Public Sans", Font.BOLD, 40));
            label1.setForeground(new Color(0x2C2E31));
            listValue.add(label1);

            JLabel label2 = new JLabel();
            label2.setFont(new Font("Public Sans", Font.PLAIN, 15));
            listTitle.add(label2);
        }

//        listIcon.get(0).setIcon(new FlatSVGIcon("icon/customerr.svg"));
//
//        try {
//            List<List<String>> numberOfCustomer = MySQL.executeQueryStatistic("SELECT COUNT(customer.id) FROM customer WHERE customer.membership = 1 and customer.deleted = 0");
//            listValue.get(0).setText(numberOfCustomer.get(0).get(0));
//        } catch (SQLException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        listTitle.get(0).setText("<html>Số lượng khách hàng thành viên hiện tại<html>");
//
//        listItem.get(0).add(listIcon.get(0));
//        listItem.get(0).add(listValue.get(0),"wrap");
//        listItem.get(0).add(listTitle.get(0),"span 2 1");
//
//        genneralTab.add(listItem.get(0));

        listIcon.get(1).setIcon(new FlatSVGIcon("icon/stafff.svg"));

        try {
            List<List<String>> numberOfStaff = MySQL.executeQueryStatistic("SELECT COUNT(staff.id) FROM staff WHERE staff.deleted = 0");
            listValue.get(1).setText(numberOfStaff.get(0).get(0));
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        listTitle.get(1).setText("<html>Số lượng nhân viên hiện tại<html>");

        listItem.get(1).add(listIcon.get(1));
        listItem.get(1).add(listValue.get(1),"wrap");
        listItem.get(1).add(listTitle.get(1),"span 2 1");

        genneralTab.add(listItem.get(1));


        try {
            List<List<String>> bestSeller= MySQL.executeQueryStatistic("SELECT product.id, product.image, SUM(receipt_detail.quantity) " +
                "FROM receipt_detail JOIN product on receipt_detail.product_id = product.id " +
                "JOIN receipt on receipt.id = receipt_detail.receipt_id " +
                "WHERE (MONTH(CURDATE()) = MONTH(`receipt`.invoice_date)) AND (YEAR(CURDATE()) = YEAR(`receipt`.invoice_date)) " +
                "GROUP BY product.id, product.image " +
                "ORDER BY SUM(receipt_detail.quantity) DESC" +
                " LIMIT 1");
            if (!bestSeller.isEmpty()) {
                listIcon.get(2).setIcon(new FlatSVGIcon(bestSeller.get(0).get(1)));
                listValue.get(2).setText(bestSeller.get(0).get(2));
                Product product = productBLL.findProductsBy(Map.of("id", Integer.parseInt(bestSeller.get(0).get(0)))).get(0);
                listTitle.get(2).setText("<html>Sản phẩm bán chạy nhất tháng: <br>" + product.getName() +"<html>");
            }else {
                listValue.get(2).setText("0");
                listTitle.get(2).setText("<html>Sản phẩm bán chạy nhất tháng:<html>");
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }


        listItem.get(2).add(listIcon.get(2));
        listItem.get(2).add(listValue.get(2),"wrap");
        listItem.get(2).add(listTitle.get(2),"span 2 1");

        genneralTab.add(listItem.get(2));

        try {
            List<List<String>> bestSeller= MySQL.executeQueryStatistic("SELECT product.id, product.image, SUM(receipt_detail.quantity) " +
                "FROM receipt_detail JOIN product on receipt_detail.product_id = product.id " +
                "JOIN receipt on receipt.id = receipt_detail.receipt_id " +
                "WHERE (MONTH(CURDATE()) = MONTH(`receipt`.invoice_date)) AND (YEAR(CURDATE()) = YEAR(`receipt`.invoice_date)) " +
                "GROUP BY product.id, product.image " +
                "ORDER BY SUM(receipt_detail.quantity) ASC" +
                " LIMIT 1");
            if (!bestSeller.isEmpty()) {
                listIcon.get(3).setIcon(new FlatSVGIcon(bestSeller.get(0).get(1)));
                listValue.get(3).setText(bestSeller.get(0).get(2));
                Product product = productBLL.findProductsBy(Map.of("id", Integer.parseInt(bestSeller.get(0).get(0)))).get(0);
                listTitle.get(3).setText("<html>Sản phẩm bán ít nhất tháng: <br>" + product.getName() +"<html>");
            }else {
                listValue.get(3).setText("0");
                listTitle.get(3).setText("<html>Sản phẩm bán ít nhất tháng:<html>");
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        listItem.get(3).add(listIcon.get(3));
        listItem.get(3).add(listValue.get(3),"wrap");
        listItem.get(3).add(listTitle.get(3),"span 2 1");

        genneralTab.add(listItem.get(3), "wrap");

        try {
            List<List<String>> bestStaff= MySQL.executeQueryStatistic("SELECT staff.id, staff.name, COUNT(receipt.id) " +
                "FROM receipt JOIN staff on receipt.staff_id = staff.id " +
                "WHERE (MONTH(CURDATE()) = MONTH(`receipt`.invoice_date)) AND (YEAR(CURDATE()) = YEAR(`receipt`.invoice_date)) " +
                "GROUP BY staff.id, staff.name " +
                "ORDER BY COUNT(receipt.id) DESC" +
                " LIMIT 1");
            listIcon.get(4).setIcon(new FlatSVGIcon("icon/stafff.svg"));

            if (!bestStaff.isEmpty()) {
                listValue.get(4).setText(bestStaff.get(0).get(2));
                Staff staff = staffBLL.findStaffsBy(Map.of("id", Integer.parseInt(bestStaff.get(0).get(0)))).get(0);
                listTitle.get(4).setText("<html>Nhân viên của tháng: <br>" + staff.getName() +"<html>");
            }else {
                listValue.get(4).setText("0");
                listTitle.get(4).setText("<html>Nhân viên của tháng:<html>");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        listItem.get(4).add(listIcon.get(4));
        listItem.get(4).add(listValue.get(4),"wrap");
        listItem.get(4).add(listTitle.get(4),"span 2 1");

        genneralTab.add(listItem.get(4));

        try {
            List<List<String>> mostInventory= MySQL.executeQueryStatistic("SELECT product.id, product.image, shipment.remain " +
                "FROM shipment JOIN product on shipment.product_id = product.id " +
                "ORDER BY shipment.remain ASC" +
                " LIMIT 1");
            if (!mostInventory.isEmpty()) {
                listIcon.get(5).setIcon(new FlatSVGIcon(mostInventory.get(0).get(1)));
                listValue.get(5).setText(mostInventory.get(0).get(2));
                Product product = productBLL.findProductsBy(Map.of("id", Integer.parseInt(mostInventory.get(0).get(0)))).get(0);
                listTitle.get(5).setText("<html>Sản phẩm tồn kho nhiều nhất: <br>" + product.getName() +"<html>");
            }else {
                listValue.get(5).setText("0");
                listTitle.get(5).setText("<html>Sản phẩm tồn kho nhiều nhất:<html>");
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        listItem.get(5).add(listIcon.get(5));
        listItem.get(5).add(listValue.get(5),"wrap");
        listItem.get(5).add(listTitle.get(5),"span 2 1");

        genneralTab.add(listItem.get(5));

        try {
            List<List<String>> mostInventory= MySQL.executeQueryStatistic("SELECT SUM(`import`.total) " +
                "FROM `import` " +
                "WHERE (MONTH(CURDATE()) - MONTH(`import`.received_date)) = 0 AND (YEAR(CURDATE()) - YEAR(`import`.received_date)) = 0");
            listIcon.get(6).setIcon(new FlatSVGIcon("icon/importGoods.svg"));
            listValue.get(6).setText("<html>" + mostInventory.get(0).get(0) + "<br>vnđ<html>");
            listTitle.get(6).setText("<html>Chi phí nhập kho của tháng<html>");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        listItem.get(6).add(listIcon.get(6), "wrap");
        listItem.get(6).add(listValue.get(6),"wrap");
        listItem.get(6).add(listTitle.get(6),"wrap");

        genneralTab.add(listItem.get(6));

        try {
            List<List<String>> mostInventory= MySQL.executeQueryStatistic("SELECT SUM(`receipt`.total) " +
                "FROM `receipt` " +
                "WHERE (MONTH(CURDATE()) - MONTH(`receipt`.invoice_date)) = 0 AND (YEAR(CURDATE()) - YEAR(`receipt`.invoice_date)) = 0");
            listIcon.get(7).setIcon(new FlatSVGIcon("icon/imcome.svg"));
            listValue.get(7).setText("<html>" + mostInventory.get(0).get(0) + "<br>vnđ<html>");
            listTitle.get(7).setText("<html>Doanh thu của tháng<html>");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        listItem.get(7).add(listIcon.get(7), "wrap");
        listItem.get(7).add(listValue.get(7),"wrap");
        listItem.get(7).add(listTitle.get(7),"wrap");

        genneralTab.add(listItem.get(7), "wrap");
//        genneralTab.add(listItem.get(8));
//        genneralTab.add(listItem.get(9));
//        genneralTab.add(listItem.get(10));
//        genneralTab.add(listItem.get(11), "wrap");

        return genneralTab;
    }

    private void statisticByYear() {
        statisticByYearPanel.setLayout(new BorderLayout());
        statisticByYearPanel.setPreferredSize(new Dimension(1140, 760));
        statisticByYearPanel.setBackground(new Color(0xFFBDD2DB, true));

        SwingUtilities.invokeLater(() -> {
            JFXPanel jfxPanel = new JFXPanel();
            Chart<String, Number> barChart = createBarChart(1);
            Scene scene = new Scene(barChart, 800, 600);

            jfxPanel.setScene(scene);
            JFXPanel newJFXPanel = new JFXPanel();
            newJFXPanel.setScene(scene);
            statisticByYearPanel.add(newJFXPanel, BorderLayout.CENTER);
            statisticByYearPanel.repaint();
            statisticByYearPanel.revalidate();
        });
    }

    private RoundedPanel statisticByQuarter() {
        statisticByQuarterPanel.setLayout(new BorderLayout());
        statisticByQuarterPanel.setPreferredSize(new Dimension(1140, 760));
        statisticByQuarterPanel.setBackground(new Color(0xFFBDD2DB, true));

        SwingUtilities.invokeLater(() -> {
            JFXPanel jfxPanel = new JFXPanel();
            Chart<String, Number> barChart = createBarChart(2);
            Scene scene = new Scene(barChart, 800, 600);

            jfxPanel.setScene(scene);
            JFXPanel newJFXPanel = new JFXPanel();
            newJFXPanel.setScene(scene);
            statisticByQuarterPanel.add(newJFXPanel, BorderLayout.CENTER);
            statisticByQuarterPanel.repaint();
            statisticByQuarterPanel.revalidate();
        });

        return statisticByQuarterPanel;
    }

    private RoundedPanel statisticByMonth() {
        statisticByMonthPanel.setLayout(new BorderLayout());
        statisticByMonthPanel.setPreferredSize(new Dimension(1140, 760));
        statisticByMonthPanel.setBackground(new Color(0xFFBDD2DB, true));

        SwingUtilities.invokeLater(() -> {
            JFXPanel jfxPanel = new JFXPanel();
            Chart<String, Number> barChart = createBarChart(3);
            Scene scene = new Scene(barChart, 800, 600);

            jfxPanel.setScene(scene);
            JFXPanel newJFXPanel = new JFXPanel();
            newJFXPanel.setScene(scene);
            statisticByMonthPanel.add(newJFXPanel, BorderLayout.CENTER);
            statisticByMonthPanel.repaint();
            statisticByMonthPanel.revalidate();
        });

        return statisticByMonthPanel;
    }

    private Chart<String, Number> createBarChart(int flag) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        Chart<String, Number> bc = new Chart<>(xAxis, yAxis);
        if (flag == 1) {
            bc.setTitle("THỐNG KÊ THEO 3 NĂM GẦN NHẤT ");
            xAxis.setLabel("Năm");
            yAxis.setLabel("VNĐ");

            int currentYear = Year.now().getValue();
            for (int i = currentYear - 2; i <= currentYear; i ++) {
                long expenses, amount;
                XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                series1.setName("Năm " + i);
                try {
                    List<List<String>> inventory= MySQL.executeQueryStatistic("SELECT SUM(`import`.total) " +
                        "FROM `import` " +
                        "WHERE YEAR(`import`.received_date) = " + i);
                    expenses = Long.parseLong(inventory.get(0).get(0).split("\\.")[0]);
                    series1.getData().add(new XYChart.Data<>("Chi phí", expenses));

                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }

                try {
                    List<List<String>> inventory= MySQL.executeQueryStatistic("SELECT SUM(`receipt`.total) " +
                        "FROM `receipt` " +
                        "WHERE YEAR(`receipt`.invoice_date) = " + i);
                    amount =  Long.parseLong(inventory.get(0).get(0).split("\\.")[0]);
                    series1.getData().add(new XYChart.Data<>("Doanh thu", amount));

                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }

                series1.getData().add(new XYChart.Data<>("Lợi nhuận", amount - expenses));

                bc.getData().add(series1);
            }
        }
        if (flag == 2) {
            xAxis.setLabel("Quý");
            yAxis.setLabel("VNĐ");

            int currentMonth = YearMonth.now().getMonthValue();
            int currentYear = Year.now().getValue();
            int currentQuarter = (currentMonth - 1) / 3 + 1;
            bc.setTitle("THỐNG KÊ THEO QUÝ TRONG NĂM " + currentYear);
            for (int i = 1; i <= currentQuarter; i++) {
                long expenses, amount;
                XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                series1.setName("Quý " + i);
                try {
                    List<List<String>> inventory= MySQL.executeQueryStatistic("SELECT SUM(`import`.total) " +
                        "FROM `import` " +
                        "WHERE YEAR(`import`.received_date) = " + currentYear + " AND QUARTER(`import`.received_date) = " + i);
                    expenses = Long.parseLong(inventory.get(0).get(0).split("\\.")[0]);
                    series1.getData().add(new XYChart.Data<>("Chi phí", expenses));

                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }

                try {
                    List<List<String>> inventory= MySQL.executeQueryStatistic("SELECT SUM(`receipt`.total) " +
                        "FROM `receipt` " +
                        "WHERE YEAR(`receipt`.invoice_date) = " + currentYear + " AND QUARTER(`receipt`.invoice_date) = " + i);
                    amount =  Long.parseLong(inventory.get(0).get(0).split("\\.")[0]);
                    series1.getData().add(new XYChart.Data<>("Doanh thu", amount));

                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }

                series1.getData().add(new XYChart.Data<>("Lợi nhuận", amount - expenses));

                bc.getData().add(series1);
            }
        }
        if (flag == 3) {
            xAxis.setLabel("Tháng");
            yAxis.setLabel("VNĐ");

            int currentMonth = YearMonth.now().getMonthValue();
            int currentYear = Year.now().getValue();
            bc.setTitle("THỐNG KÊ THEO THÁNG TRONG NĂM " + currentYear);
            for (int i = 1; i <= currentMonth; i++) {
                long expenses, amount;
                XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                series1.setName("Tháng " + i);
                try {
                    List<List<String>> inventory= MySQL.executeQueryStatistic("SELECT SUM(`import`.total) " +
                        "FROM `import` " +
                        "WHERE YEAR(`import`.received_date) = " + currentYear + " AND MONTH(`import`.received_date) = " + i);
                    expenses = Long.parseLong(inventory.get(0).get(0).split("\\.")[0]);
                    series1.getData().add(new XYChart.Data<>("Chi phí", expenses));

                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }

                try {
                    List<List<String>> inventory= MySQL.executeQueryStatistic("SELECT SUM(`receipt`.total) " +
                        "FROM `receipt` " +
                        "WHERE YEAR(`receipt`.invoice_date) = " + currentYear + " AND MONTH(`receipt`.invoice_date) = " + i);
                    amount = Long.parseLong(inventory.get(0).get(0).split("\\.")[0]);
                    series1.getData().add(new XYChart.Data<>("Doanh thu", amount));

                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }

                series1.getData().add(new XYChart.Data<>("Lợi nhuận", amount - expenses));

                bc.getData().add(series1);
            }
        }
        return bc;
    }

}
