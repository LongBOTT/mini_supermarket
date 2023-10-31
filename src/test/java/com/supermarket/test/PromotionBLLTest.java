package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.PromotionBLL;
import com.supermarket.DTO.Promotion;
import com.supermarket.utils.Date;

public class PromotionBLLTest{
    PromotionBLL promotionBLL = new PromotionBLL();

    Promotion promotion = new Promotion(1,new Date(20,11,2023),new Date(20,12,2023),false,false); 

    @Test
    public void arrayTest(){
        for(Promotion promotionRow : promotionBLL.getPromotionList()){
            System.out.println(promotionRow.getEnd_date());
        }
    }

    @Test
    public void addTest() {
        boolean success = promotionBLL.addPromotion(promotion);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        promotion.setStatus(true);
        boolean success = promotionBLL.updatePromotion(promotion);
        assertTrue(success);
    }

    @Test
    public void removeTest() {
        boolean success = promotionBLL.deletePromotion(promotion);
        assertTrue(success);
    }
}