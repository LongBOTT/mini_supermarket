package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.DecentralizationBLL;
import com.supermarket.DTO.Decentralization;

public class DecentralizationBLLTest{
    DecentralizationBLL decentralizationBLL = new DecentralizationBLL();

    Decentralization decentralization = new Decentralization(1,14,4);

    @Test
    public void arrayTest(){
        for(Decentralization decentralizationRow : decentralizationBLL.getDecentralizationList()){
            System.out.println(decentralizationRow.getRole_id());
        }
    }

    @Test
    public void addTest() {
        boolean success = decentralizationBLL.addDecentralization(decentralization);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        decentralization.setFunction_id(3);
        boolean success = decentralizationBLL.updateDecentralization(decentralization);
        assertTrue(success);
    }
}