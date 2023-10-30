package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.ProductBLL;
import com.supermarket.DTO.Product;

public class ProductBLLTest{
    ProductBLL productBLL = new ProductBLL();

    Product product = new Product(41, "Tea+", 17, 6, "Lốc", 85000, 0, "img/products/teaplus.png", "", false); 

    @Test
    public void arrayTest(){
        for(Product productRow : productBLL.getProductList()){
            
        }
    }

    @Test
    public void addTest() {
        boolean success = productBLL.addProduct(product);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        product.setName("Tea Plus Bí Đao");
        boolean success = productBLL.updateProduct(product);
        assertTrue(success);
    }

    @Test
    public void removeTest() {
        boolean success = productBLL.deleteProduct(product);
        assertTrue(success);
    }
}