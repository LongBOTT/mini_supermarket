package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.Receipt_detailBLL;
import com.supermarket.DTO.Receipt_detail;

public class Receipt_detailBLLTest{
    Receipt_detailBLL receiptBLL = new Receipt_detailBLL();

    Receipt_detail receipt = new Receipt_detail(1, 1, 10, 2, 0.2); 

    @Test
    public void arrayTest(){
        for(Receipt_detail receiptRow : receiptBLL.getReceipt_detailList()){
            System.out.println(receiptRow.getQuantity());
        }
    }

    @Test
    public void addTest() {
        boolean success = receiptBLL.addReceipt_detail(receipt);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        receipt.setTotal(30);
        boolean success = receiptBLL.updateReceipt_detail(receipt);
        assertTrue(success);
    }
}