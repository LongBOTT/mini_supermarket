package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.ExportBLL;
import com.supermarket.DTO.Export;
import com.supermarket.utils.Date;

public class ExportBLLTest{
    ExportBLL exportBLL = new ExportBLL();

    Export export = new Export(2, 1, new Date(22, 10, 2023), 10, "ok",false); 

    @Test
    public void arrayTest(){
        for(Export exportRow : exportBLL.getExportList()){
            System.out.println(exportRow.getId());
        }
    }

    @Test
    public void addTest() {
        boolean success = exportBLL.addExport(export);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        export.setReason("Brok");
        boolean success = exportBLL.updateExport(export);
        assertTrue(success);
    }

    @Test
    public void removeTest() {
        boolean success = exportBLL.deleteExport(export);
        assertTrue(success);
    }
}