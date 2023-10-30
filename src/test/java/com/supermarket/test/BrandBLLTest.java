package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.BrandBLL;
import com.supermarket.DTO.Brand;

public class BrandBLLTest{
    BrandBLL brandBLL = new BrandBLL();

    Brand brand = new Brand(1, "Asd", 2, false); 

    @Test
    public void arrayTest(){
        for(Brand brandRow : brandBLL.getBrandList()){
            System.out.println(brandRow.getName());
        }
    }

    @Test
    public void addTest() {
        boolean success = brandBLL.addBrand(brand);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        brand.setName("Cokacola");
        boolean success = brandBLL.updateBrand(brand);
        assertTrue(success);
    }

    @Test
    public void removeTest() {
        boolean success = brandBLL.deleteBrand(brand);
        assertTrue(success);
    }
}