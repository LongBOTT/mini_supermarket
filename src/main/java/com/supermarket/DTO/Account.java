package com.supermarket.DTO;

public class Account {
    private int id;
    private String username;
    private String password;
    private int roleID;
    private int staffID;
    private boolean deleted;

    public Account() {
    }

    public Account(int id, String username, String password, int roleID, int staffID, boolean deleted) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleID = roleID;
        this.staffID = staffID;
        this.deleted = deleted;
    }

    public int getAccountID() {
        return id;
    }

    public void setAccountID(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return id + " | " +
            username + " | " +
            password + " | " +
            roleID + " | " +
            staffID;
    }
}
