package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.DiscountBLL;
import com.supermarket.DTO.Discount;
import com.supermarket.utils.Date;

public class DiscountBLLTest{
    DiscountBLL discountBLL = new DiscountBLL();

    Discount discount = new Discount(1, 0.15, new Date(20,2,2023), new Date(20,3,2023),false, false); 

    @Test
    public void arrayTest(){
        for(Discount discountRow : discountBLL.getDiscountList()){
            System.out.println(discountRow.getEnd_date());
        }
    }

    @Test
    public void addTest() {
        boolean success = discountBLL.addDiscount(discount);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        discount.setPercent(0.2);
        boolean success = discountBLL.updateDiscount(discount);
        assertTrue(success);
    }

    @Test
    public void removeTest() {
        boolean success = discountBLL.deleteDiscount(discount);
        assertTrue(success);
    }
}