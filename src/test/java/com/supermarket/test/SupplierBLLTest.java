package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.SupplierBLL;
import com.supermarket.DTO.Supplier;
import com.supermarket.utils.Date;

public class SupplierBLLTest{
    SupplierBLL supplierBLL = new SupplierBLL();

    Supplier supplier = new Supplier(5, "Cương Dương", "092000223", "196 Bùi Hữu Ỉa", "202@gmail.com", false); 

    @Test
    public void arrayTest(){
        for(Supplier supplierRow : supplierBLL.getSupplierList()){
            System.out.println(supplierRow.getName());
        }
    }

    @Test
    public void addTest() {
        boolean success = supplierBLL.addSupplier(supplier);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        supplier.setName("Cưng Dương");
        boolean success = supplierBLL.updateSupplier(supplier);
        assertTrue(success);
    }

    @Test
    public void removeTest() {
        boolean success = supplierBLL.deleteSupplier(supplier);
        assertTrue(success);
    }
}