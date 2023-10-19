package com.supermarket.BLL;

import com.supermarket.DAL.SupplierDAL;
import com.supermarket.DTO.Supplier;

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
        supplierList.add(supplier);
        return supplierDAL.addSupplier(supplier) != 0;
    }

    public boolean updateSupplier(Supplier supplier) {
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

    public boolean exists(Supplier supplier) {
        return !findSuppliersBy(Map.of(
            "id", supplier.getId(),
            "name", supplier.getName(),
            "phone", supplier.getPhone(),
            "address", supplier.getAddress(),
            "email", supplier.getEmail()
        )).isEmpty();
    }

    public boolean exists(Map<String, Object> conditions) {
        return !findSuppliersBy(conditions).isEmpty();
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
