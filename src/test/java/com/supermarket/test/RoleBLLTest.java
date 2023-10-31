package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.RoleBLL;
import com.supermarket.DTO.Role;
import com.supermarket.utils.Date;

public class RoleBLLTest{
    RoleBLL roleBLL = new RoleBLL();

    Role role = new Role(5, "Lao công", false); 

    @Test
    public void arrayTest(){
        for(Role roleRow : roleBLL.getRoleList()){
            System.out.println(roleRow.getName());
        }
    }

    @Test
    public void addTest() {
        boolean success = roleBLL.addRole(role);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        role.setName("Thư ký");
        boolean success = roleBLL.updateRole(role);
        assertTrue(success);
    }

    @Test
    public void removeTest() {
        boolean success = roleBLL.deleteRole(role);
        assertTrue(success);
    }
}