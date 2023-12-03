package com.supermarket.BLL;

import com.supermarket.DAL.StaffDAL;
import com.supermarket.DTO.Staff;
import com.supermarket.utils.Date;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.supermarket.utils.Date.parseDate;

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



    public boolean addStaff(Staff staff) throws Exception {
        if (!validateName(staff.getName())) {
            JOptionPane.showMessageDialog(null,"Tên không hợp lệ." ,"Thông báo",JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if(!validatePhone(staff.getPhone())){
            JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ." ,"Thông báo",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!validateEmail(staff.getEmail())){
            JOptionPane.showMessageDialog(null,"Email không hợp lệ." ,"Thông báo",JOptionPane.ERROR_MESSAGE);
            return false;
        }



        if (exists(staff.getPhone(),staff.getEmail())) {
            return false;
        }


//        if (!validateDate(staff.getBirthday())) {
////            JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ.", "Thông báo", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//
//        if (!validateDate(staff.getEntry_date())) {
////            JOptionPane.showMessageDialog(null, "Ngày vào làm không hợp lệ.", "Thông báo", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }




        staffList.add(staff);
        return staffDAL.addStaff(staff) != 0;
    }

    public boolean updateStaff(Staff staff) {
        if (!validateName(staff.getName())) {
            JOptionPane.showMessageDialog(null,"Tên không hợp lệ." ,"Thông báo",JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if(!validatePhone(staff.getPhone())){
            JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ." ,"Thông báo",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!validateEmail(staff.getEmail())){
            JOptionPane.showMessageDialog(null,"Email không hợp lệ." ,"Thông báo",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (exists(staff.getPhone(),staff.getEmail())) {
            return false;
        }
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

//    public boolean exists(Staff staff) {
//        return !findStaffsBy(Map.of(
//            "name", staff.getName(),
//            "gender", staff.getGender(),
//            "birthday", staff.getBirthday(),
//            "phone", staff.getPhone(),
//            "address", staff.getAddress(),
//            "email", staff.getEmail(),
//            "entry_date", staff.getEntry_date()
//        )).isEmpty();
//    }
//
//    public boolean exists(Map<String, Object> conditions) {
//        return !findStaffsBy(conditions).isEmpty();
//    }


    public boolean exists(String phone, String email) {
        for (Staff staff : staffList) {
            if (staff.getPhone().equals(phone)) {
                JOptionPane.showMessageDialog(null, "Số điện thoại nhân viên đã tồn tại.", "Thông báo", JOptionPane.ERROR_MESSAGE);

                return true;
            }
            if(staff.getEmail().equals(email)){
                JOptionPane.showMessageDialog(null, "Email nhân viên đã tồn tại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }
        return false;
    }

    public boolean validateName(String name) {
        // Kiểm tra tên có tồn tại và có đúng định dạng tiếng Việt có dấu câu, có thể viết hoa, viết thường, không được để trống
        return name != null && !name.isEmpty() && name.matches("[\\p{L}\\p{P}\\s]+");
    }

    public  boolean validatePhone(String phone){
        return phone.matches("^(\\+?84|0)[35789]\\d{8}$");
    }


    public boolean validateEmail(String email){
        return email.matches("^\\w+(\\.\\w+)*@\\w+(\\.\\w+)+");
    }


//    public boolean validateDate(Date date) throws Exception {
//
//        if (!ParseDate(date.toString())) {
//            JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ.", "Thông báo", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//
//        int day = date.getDate();
//        int month = date.getMonth();
//        int year = date.getYear();
//        if (!Date.isValidDay(day, month, year)) {
//            JOptionPane.showMessageDialog(null, "Ngày không hợp lệ.", "Thông báo", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//
//        return true;
//    }
//    public static boolean ParseDate(String str) {
//        if (str.matches("^\\d{4}[-/]((0?[1-9]|1[0-2])[-/](0?[1-9]|[12][0-9]|3[01]))$")) {
//            return true;
//        } else {
//            return false;
//        }
//    }



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
