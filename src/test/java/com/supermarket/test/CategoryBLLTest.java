package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.CategoryBLL;
import com.supermarket.DTO.Category;

public class CategoryBLLTest{
    CategoryBLL categoryBLL = new CategoryBLL();

    Category category = new Category(8, "Sua", 0, false); 

    @Test
    public void arrayTest(){
        for(Category categoryRow : categoryBLL.getCategoryList()){
            System.out.println(categoryRow.getName());
        }
    }

    @Test
    public void addTest() {
        boolean success = categoryBLL.addCategory(category);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        category.setName("Cokacola");
        boolean success = categoryBLL.updateCategory(category);
        assertTrue(success);
    }

    @Test
    public void removeTest() {
        boolean success = categoryBLL.deleteCategory(category);
        assertTrue(success);
    }
}