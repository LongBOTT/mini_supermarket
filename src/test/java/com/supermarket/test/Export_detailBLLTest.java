package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.Export_detailBLL;
import com.supermarket.DTO.Export_detail;

public class Export_detailBLLTest{
    Export_detailBLL export_detailBLL = new Export_detailBLL();

    Export_detail export_detail = new Export_detail(1, 1, 10, 10); 

    @Test
    public void arrayTest(){
        for(Export_detail export_detailRow : export_detailBLL.getExportList()){
            System.out.println(export_detailRow.getQuantity());
        }
    }

    @Test
    public void addTest() {
        boolean success = export_detailBLL.addExport(export_detail);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        export_detail.setQuantity(40);
        boolean success = export_detailBLL.updateExport(export_detail);
        assertTrue(success);
    }
}