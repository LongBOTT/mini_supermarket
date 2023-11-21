package com.supermarket.BLL;

import com.supermarket.DAL.DiscountDAL;
import com.supermarket.DTO.Discount;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiscountBLL extends Manager<Discount> {
    private DiscountDAL discountDAL;
    private List<Discount> discountList;

    public DiscountBLL() {
        discountDAL = new DiscountDAL();
        discountList = searchDiscounts();
    }

    public DiscountDAL getDiscountDAL() {
        return discountDAL;
    }

    public void setDiscountDAL(DiscountDAL discountDAL) {
        this.discountDAL = discountDAL;
    }

    public List<Discount> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<Discount> discountList) {
        this.discountList = discountList;
    }

    public Object[][] getData() {
        return getData(discountList);
    }

    public boolean addDiscount(Discount discount) {
        discountList.add(discount);
        return discountDAL.addDiscount(discount) != 0;
    }

    public boolean updateDiscount(Discount discount) {
        discountList.set(getIndex(discount, "id", discountList), discount);
        return discountDAL.updateDiscount(discount) != 0;
    }

    public List<Discount> searchDiscounts(String... conditions) {
        return discountDAL.searchDiscounts(conditions);
    }

    public List<Discount> findDiscounts(String key, String value) {
        List<Discount> list = new ArrayList<>();
        for (Discount discount : discountList) {
            if (getValueByKey(discount, key).toString().toLowerCase().contains(value.toLowerCase())) {
                list.add(discount);
            }
        }
        return list;
    }

    public List<Discount> findDiscountsBy(Map<String, Object> conditions) {
        List<Discount> discounts = discountList;
        for (Map.Entry<String, Object> entry : conditions.entrySet())
            discounts = findObjectsBy(entry.getKey(), entry.getValue(), discounts);
        return discounts;
    }

    public boolean exists(Discount discount) {
        return !findDiscountsBy(Map.of(
            "id", discount.getId(),
            "percent", discount.getPercent(),
            "start_date", discount.getStart_date(),
            "end_date",discount.getEnd_date(),
            "status",discount.isStatus()
        )).isEmpty();
    }

    public boolean exists(Map<String, Object> conditions) {
        return !findDiscountsBy(conditions).isEmpty();
    }

    @Override
    public Object getValueByKey(Discount discount, String key) {
        return switch (key) {
            case "id" -> discount.getId();
            case "percent" -> discount.getPercent();
            case "start_date" -> discount.getStart_date();
            case "end_date" -> discount.getEnd_date();
            case "status" -> discount.isStatus();
            default -> null;
        };
    }
}
