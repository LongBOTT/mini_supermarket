package com.supermarket.BLL;

import com.supermarket.DAL.StaffDAL;
import com.supermarket.DTO.Staff;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StaffBLL extends Manager<Staff>{
    private StaffDAL staffDAL;
    private List<Staff> staffList;

    public StaffBLL() {
        staffDAL = new StaffDAL();
        staffList = searchStaffs("deleted = 0");
    }

    public StaffDAL getStaffDAL() {
        return staffDAL;
    }

    public void setStaffDAL(StaffDAL staffDAL) {
        this.staffDAL = staffDAL;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public Object[][] getData() {
        return getData(staffList);
    }

    public boolean addStaff(Staff staff) {
        staffList.add(staff);
        return staffDAL.addStaff(staff) != 0;
    }

    public boolean updateStaff(Staff staff) {
        staffList.set(getIndex(staff, "id", staffList), staff);
        return staffDAL.updateStaff(staff) != 0;
    }

    public boolean deleteStaff(Staff staff) {
        staffList.remove(getIndex(staff, "id", staffList));
        return staffDAL.deleteStaff("id = " + staff.getId()) != 0;
    }

    public List<Staff> searchStaffs(String... conditions) {
        return staffDAL.searchStaffs(conditions);
    }

    public List<Staff> findStaffs(String key, String value) {
        List<Staff> list = new ArrayList<>();
        for (Staff staff : staffList) {
            if (getValueByKey(staff, key).toString().toLowerCase().contains(value.toLowerCase())) {
                list.add(staff);
            }
        }
        return list;
    }

    public List<Staff> findStaffsBy(Map<String, Object> conditions) {
        List<Staff> staffs = staffList;
        for (Map.Entry<String, Object> entry : conditions.entrySet())
            staffs = findObjectsBy(entry.getKey(), entry.getValue(), staffs);
        return staffs;
    }

    public boolean exists(Staff staff) {
        return !findStaffsBy(Map.of(
            "name", staff.getName(),
            "gender", staff.getGender(),
            "birthday", staff.getBirthday(),
            "phone", staff.getPhone(),
            "address", staff.getAddress(),
            "email", staff.getEmail(),
            "entry_date", staff.getEntry_date()
        )).isEmpty();
    }

    public boolean exists(Map<String, Object> conditions) {
        return !findStaffsBy(conditions).isEmpty();
    }

    @Override
    public Object getValueByKey(Staff staff, String key) {
        return switch (key) {
            case "id" -> staff.getId();
            case "name" -> staff.getName();
            case "gender" -> staff.getGender();
            case "birthday" -> staff.getBirthday();
            case "phone" -> staff.getPhone();
            case "address" -> staff.getAddress();
            case "email" -> staff.getEmail();
            case "entry_date" -> staff.getEntry_date();
            default -> null;
        };
    }
}
