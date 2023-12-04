package com.supermarket.BLL;

import com.supermarket.DAL.SupplierDAL;
import com.supermarket.DTO.Staff;
import com.supermarket.DTO.Supplier;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SupplierBLL extends Manager<Supplier> {
    private SupplierDAL supplierDAL;
    private List<Supplier> supplierList;

    public SupplierBLL() {
        supplierDAL = new SupplierDAL();
        supplierList = searchSuppliers("deleted = 0");
    }

    public SupplierDAL getSupplierDAL() {
        return supplierDAL;
    }

    public void setSupplierDAL(SupplierDAL supplierDAL) {
        this.supplierDAL = supplierDAL;
    }

    public List<Supplier> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<Supplier> supplierList) {
        this.supplierList = supplierList;
    }

    public Object[][] getData() {
        return getData(supplierList);
    }

    public boolean addSupplier(Supplier supplier) {
        if (!validateName(supplier.getName())) {
            JOptionPane.showMessageDialog(null,"Tên không hợp lệ." ,"Thông báo",JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if(!validatePhone(supplier.getPhone())){
            JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ." ,"Thông báo",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!validateEmail(supplier.getEmail())){
            JOptionPane.showMessageDialog(null,"Email không hợp lệ." ,"Thông báo",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (exists(supplier.getPhone(),supplier.getEmail())) {
            return false;
        }
        supplierList.add(supplier);
        return supplierDAL.addSupplier(supplier) != 0;
    }

    public boolean updateSupplier(Supplier supplier) {
        if (!validateName(supplier.getName())) {
            JOptionPane.showMessageDialog(null,"Tên không hợp lệ." ,"Thông báo",JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if(!validatePhone(supplier.getPhone())){
            JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ." ,"Thông báo",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!validateEmail(supplier.getEmail())){
            JOptionPane.showMessageDialog(null,"Email không hợp lệ." ,"Thông báo",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (exists(supplier.getPhone(),supplier.getEmail())) {
            return false;
        }
        supplierList.set(getIndex(supplier, "id", supplierList), supplier);
        return supplierDAL.updateSupplier(supplier) != 0;
    }

    public boolean deleteSupplier(Supplier supplier) {
        supplierList.remove(getIndex(supplier, "id", supplierList));
        return supplierDAL.deleteSupplier("id = " + supplier.getId()) != 0;
    }

    public List<Supplier> searchSuppliers(String... conditions) {
        return supplierDAL.searchSuppliers(conditions);
    }

    public List<Supplier> findSuppliers(String key, String value) {
        List<Supplier> list = new ArrayList<>();
        for (Supplier supplier : supplierList) {
            if (getValueByKey(supplier, key).toString().toLowerCase().contains(value.toLowerCase())) {
                list.add(supplier);
            }
        }
        return list;
    }

    public List<Supplier> findSuppliersBy(Map<String, Object> conditions) {
        List<Supplier> suppliers = supplierList;
        for (Map.Entry<String, Object> entry : conditions.entrySet())
            suppliers = findObjectsBy(entry.getKey(), entry.getValue(), suppliers);
        return suppliers;
    }

//    public boolean exists(Supplier supplier) {
//        return !findSuppliersBy(Map.of(
//            "id", supplier.getId(),
//            "name", supplier.getName(),
//            "phone", supplier.getPhone(),
//            "address", supplier.getAddress(),
//            "email", supplier.getEmail()
//        )).isEmpty();
//    }
//
//    public boolean exists(Map<String, Object> conditions) {
//        return !findSuppliersBy(conditions).isEmpty();
//    }

    public boolean exists(String phone, String email) {
        for (Supplier supplier : supplierList) {
            if (supplier.getPhone().equals(phone)) {
                JOptionPane.showMessageDialog(null, "Số điện thoại nhà cung cấp đã tồn tại.", "Thông báo", JOptionPane.ERROR_MESSAGE);

                return true;
            }
            if(supplier.getEmail().equals(email)){
                JOptionPane.showMessageDialog(null, "Email nhà cung cấp đã tồn tại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
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



    @Override
    public Object getValueByKey(Supplier supplier, String key) {
        return switch (key) {
            case "id" -> supplier.getId();
            case "name" -> supplier.getName();
            case "phone" -> supplier.getPhone();
            case "address" -> supplier.getAddress();
            case "email" -> supplier.getEmail();

            default -> null;
        };
    }
}
