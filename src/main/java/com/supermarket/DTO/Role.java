package com.supermarket.DTO;

public class Role {
    private int roleID;
    private String name;
    private boolean deleted;

    public Role() {
    }

    public Role(int roleID, String name, boolean deleted) {
        this.roleID = roleID;
        this.name = name;
        this.deleted = deleted;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return roleID + " | " +
            name;
    }
}
