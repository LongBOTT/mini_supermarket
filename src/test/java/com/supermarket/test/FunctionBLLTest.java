package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.FunctionBLL;
import com.supermarket.DTO.Function;
import com.supermarket.utils.Date;

public class FunctionBLLTest{
    FunctionBLL functionBLL = new FunctionBLL();

    Function function = new Function(5, "Huỷ", false); 

    @Test
    public void arrayTest(){
        for(Function functionRow : functionBLL.getFunctionList()){
            System.out.println(functionRow.getName());
        }
    }

    @Test
    public void addTest() {
        boolean success = functionBLL.addFunction(function);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        function.setName("Huỷ diệt 2");
        boolean success = functionBLL.updateFunction(function);
        assertTrue(success);
    }

    @Test
    public void removeTest() {
        boolean success = functionBLL.deleteFunction(function);
        assertTrue(success);
    }
}