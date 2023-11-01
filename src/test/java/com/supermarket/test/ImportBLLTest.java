package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.ImportBLL;
import com.supermarket.DTO.Import;
import com.supermarket.utils.Date;

public class ImportBLLTest{
    ImportBLL importNBLL = new ImportBLL();

    Import importN = new Import(2, 1, new Date(10,10,2020), 10, 1, false); 

    @Test
    public void arrayTest(){
        for(Import importNRow : importNBLL.getImportList()){
            System.out.println(importNRow.getId());
        }
    }

    @Test
    public void addTest() {
        boolean success = importNBLL.addImport(importN);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        importN.setTotal(20);;
        boolean success = importNBLL.updateImport(importN);
        assertTrue(success);
    }

    @Test
    public void removeTest() {
        boolean success = importNBLL.deleteImport(importN);
        assertTrue(success);
    }
}