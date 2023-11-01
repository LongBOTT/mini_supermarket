package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.ReceiptBLL;
import com.supermarket.DTO.Receipt;
import com.supermarket.utils.Date;

public class ReceiptBLLTest{
    ReceiptBLL receiptBLL = new ReceiptBLL();

    Receipt receipt = new Receipt(1, 1, 1, new Date(10,10,2023), 10, 1, 2, false); 

    @Test
    public void arrayTest(){
        for(Receipt receiptRow : receiptBLL.getReceiptList()){
            System.out.println(receiptRow.getId());
        }
    }

    @Test
    public void addTest() {
        boolean success = receiptBLL.addReceipt(receipt);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        receipt.setTotal(30);
        boolean success = receiptBLL.updateReceipt(receipt);
        assertTrue(success);
    }

    @Test
    public void removeTest() {
        boolean success = receiptBLL.deleteReceipt(receipt);
        assertTrue(success);
    }
}