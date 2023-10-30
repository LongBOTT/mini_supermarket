package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.CustomerBLL;
import com.supermarket.DTO.Customer;
import com.supermarket.utils.Date;

public class CustomerBLLTest{
    CustomerBLL customerBLL = new CustomerBLL();

    Customer customer = new Customer(12,"Vo Mi",true,new Date(23, 12, 2032),"093020302",true,new Date(22,10,2003),10,false); 

    @Test
    public void arrayTest(){
        for(Customer customerRow : customerBLL.getCustomerList()){
            System.out.println(customerRow.getName());
        }
    }

    @Test
    public void addTest() {
        boolean success = customerBLL.addCustomer(customer);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        customer.setName("Cokac23");
        boolean success = customerBLL.updateCustomer(customer);
        assertTrue(success);
    }

    @Test
    public void removeTest() {
        boolean success = customerBLL.deleteCustomer(customer);
        assertTrue(success);
    }
}