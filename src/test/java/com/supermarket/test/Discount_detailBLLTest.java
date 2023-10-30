package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.Discount_detailBLL;
import com.supermarket.DTO.Discount_detail;

public class Discount_detailBLLTest{
    Discount_detailBLL discount_detailBLL = new Discount_detailBLL();

    Discount_detail discount_detail = new Discount_detail(1, 1, false); 

    @Test
    public void arrayTest(){
        for(Discount_detail discount_detailRow : discount_detailBLL.getDiscount_detailList()){
            System.out.println(discount_detailRow.getDiscount_id()+" "+discount_detailRow.getProduct_id());
        }
    }

    @Test
    public void addTest() {
        boolean success = discount_detailBLL.addDiscount_detail(discount_detail);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        discount_detail.setStatus(true);
        boolean success = discount_detailBLL.updateDiscount_detail(discount_detail);
        assertTrue(success);
    }
}