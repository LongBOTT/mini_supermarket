package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.ModuleBLL;
import com.supermarket.DTO.Module;
import com.supermarket.utils.Date;

public class ModuleBLLTest{
    ModuleBLL moduleBLL = new ModuleBLL();

    Module module = new Module(15, "Quản lý tiền bạc", false); 

    @Test
    public void arrayTest(){
        for(Module moduleRow : moduleBLL.getModuleList()){
            System.out.println(moduleRow.getName());
        }
    }

    @Test
    public void addTest() {
        boolean success = moduleBLL.addModule(module);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        module.setName("Quản lí tiền bạc");
        boolean success = moduleBLL.updateModule(module);
        assertTrue(success);
    }

    @Test
    public void removeTest() {
        boolean success = moduleBLL.deleteModule(module);
        assertTrue(success);
    }
}