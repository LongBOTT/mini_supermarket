package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.Promotion_giftBLL;
import com.supermarket.DTO.Promotion_gift;
import com.supermarket.utils.Date;

public class Promotion_giftBLLTest{
    Promotion_giftBLL promotionBLL = new Promotion_giftBLL();

    Promotion_gift promotion = new Promotion_gift(1, 1, 2); 

    @Test
    public void arrayTest(){
        for(Promotion_gift promotionRow : promotionBLL.getPromotion_giftList()){
            System.out.println(promotionRow.getProduct_id());
        }
    }

    @Test
    public void addTest() {
        boolean success = promotionBLL.addPromotion_gift(promotion);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        promotion.setProduct_id(2);
        boolean success = promotionBLL.updatePromotion_gift(promotion);
        assertTrue(success);
    }
}