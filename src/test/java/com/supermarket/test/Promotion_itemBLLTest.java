package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.Promotion_itemBLL;
import com.supermarket.DTO.Promotion_item;

public class Promotion_itemBLLTest{
    Promotion_itemBLL promotionBLL = new Promotion_itemBLL();

    Promotion_item promotion = new Promotion_item(1, 1, 2); 

    @Test
    public void arrayTest(){
        for(Promotion_item promotionRow : promotionBLL.getPromotion_itemList()){
            System.out.println(promotionRow.getProduct_id());
        }
    }

    @Test
    public void addTest() {
        boolean success = promotionBLL.addPromotion_item(promotion);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        promotion.setProduct_id(3);
        boolean success = promotionBLL.updatePromotion_item(promotion);
        assertTrue(success);
    }
}