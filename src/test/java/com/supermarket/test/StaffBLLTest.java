package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.StaffBLL;
import com.supermarket.DTO.Staff;
import com.supermarket.utils.Date;

public class StaffBLLTest{
    StaffBLL staffBLL = new StaffBLL();

    Staff staff = new Staff(11, "DƯƠNG MINH LÒI",true,new Date(20, 10, 1998),"029392020","209 DBC","2392@gmail.com",new Date(10,10,2021),false); 

    @Test
    public void arrayTest(){
        for(Staff staffRow : staffBLL.getStaffList()){
            System.out.println(staffRow.getName());
        }
    }

    @Test
    public void addTest() {
        boolean success = staffBLL.addStaff(staff);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        staff.setName("DƯƠNG MINH LỘI");
        boolean success = staffBLL.updateStaff(staff);
        assertTrue(success);
    }

    @Test
    public void removeTest() {
        boolean success = staffBLL.deleteStaff(staff);
        assertTrue(success);
    }
}