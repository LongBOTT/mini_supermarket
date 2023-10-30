package com.supermarket.DAL;

import com.supermarket.DTO.Receipt;
import com.supermarket.utils.Date;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDAL extends Manager {
    public ReceiptDAL() {
        super("receipt",
            List.of("id",
                "staff_id",
                "customer_id",
                "invoice_date",
                "total",
                "received",
                "excess",
                "deleted"));
    }

    public List<Receipt> convertToReceipts(List<List<String>> data) {
        return convert(data, row -> {
            try {
                return new Receipt(
                    Integer.parseInt(row.get(0)), // id
                    Integer.parseInt(row.get(1)), // staff_id
                    Integer.parseInt(row.get(2)), // customer_id
                    Date.parseDate(row.get(3)), // invoice_date
                    Double.parseDouble(row.get(4)), // total
                    Double.parseDouble(row.get(5)), // received
                    Double.parseDouble(row.get(6)), // excess
                    Boolean.parseBoolean(row.get(7))    // deleted
                );
            } catch (Exception e) {
                System.out.println("Error occurred in ReceiptDAL.convertToReceipts(): " + e.getMessage());
            }
            return new Receipt();
        });
    }

    public int addReceipt(Receipt receipt) {
        try {
            return create(receipt.getId(),
                receipt.getStaff_id(),
                receipt.getCustomer_id(),
                receipt.getInvoice_date(),
                receipt.getTotal(),
                receipt.getReceived(),
                receipt.getExcess(),
                false
            ); // receipt khi tạo mặc định deleted = 0
        } catch (SQLException | IOException e) {
            System.out.println("Error occurred in ReceiptDAL.addReceipt(): " + e.getMessage());
        }
        return 0;
    }

    public int updateReceipt(Receipt receipt) {
        try {
            List<Object> updateValues = new ArrayList<>();
            updateValues.add(receipt.getId());
            updateValues.add(receipt.getStaff_id());
            updateValues.add(receipt.getCustomer_id());
            updateValues.add(receipt.getInvoice_date());
            updateValues.add(receipt.getTotal());
            updateValues.add(receipt.getReceived());
            updateValues.add(receipt.getExcess());
            updateValues.add(receipt.isDeleted());
            return update(updateValues, "id = " + receipt.getId());
        } catch (SQLException | IOException e) {
            System.out.println("Error occurred in ReceiptDAL.updateReceipt(): " + e.getMessage());
        }
        return 0;
    }

    public int deleteReceipt(String... conditions) {
        try {
            List<Object> updateValues = new ArrayList<>();
            updateValues.add(true);
            return update(updateValues, conditions);
        } catch (SQLException | IOException e) {
            System.out.println("Error occurred in ReceiptDAL.deleteReceipt(): " + e.getMessage());
        }
        return 0;
    }

    public List<Receipt> searchReceipts(String... conditions) {
        try {
            return convertToReceipts(read(conditions));
        } catch (SQLException | IOException e) {
            System.out.println("Error occurred in ReceiptDAL.searchReceipts(): " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
