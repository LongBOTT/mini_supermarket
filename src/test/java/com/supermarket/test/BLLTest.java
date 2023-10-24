package com.supermarket.test;

import java.util.List;

import javax.management.relation.Role;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;

import com.supermarket.BLL.*;
import com.supermarket.DTO.*;
import com.supermarket.DTO.Module;

class TestClass{
    Class<?> BLLClass;
    Class<?> DTOClass;

    public TestClass(Class<?> bLLClass, Class<?> dTOClass) {
        BLLClass = bLLClass;
        DTOClass = dTOClass;
    }
}
public class BLLTest {
    
    private static List<TestClass> BLLClasses;

    @BeforeAll
    public static void setup(){
        BLLClasses = List.of(
        new TestClass(AccountBLL.class,Account.class),
        new TestClass(BrandBLL.class,Brand.class),
        new TestClass(CategoryBLL.class,Category.class),
        new TestClass(CustomerBLL.class,Customer.class),
        new TestClass(DecentralizationBLL.class,Decentralization.class),
        new TestClass(Discount_detailBLL.class,Discount_detail.class),
        new TestClass(DiscountBLL.class,Discount.class),
        new TestClass(Export_detailBLL.class,Export_detail.class),
        new TestClass(ExportBLL.class,Export.class),
        new TestClass(FunctionBLL.class,Function.class),
        new TestClass(ImportBLL.class,Import.class),
        new TestClass(ModuleBLL.class,Module.class),
        new TestClass(ProductBLL.class,Product.class),
        new TestClass(Promotion_giftBLL.class, Promotion_gift.class),
        new TestClass(Promotion_itemBLL.class, Promotion_item.class),
        new TestClass(PromotionBLL.class, Promotion.class),
        new TestClass(Receipt_detailBLL.class, Receipt_detail.class),
        new TestClass(ReceiptBLL.class, Receipt.class),
        new TestClass(RoleBLL.class, Role.class),
        new TestClass(ShipmentBLL.class,Shipment.class),
        new TestClass(StaffBLL.class,Staff.class),
        new TestClass(StatisticBLL.class,Statistic.class),
        new TestClass(SupplierBLL.class,Supplier.class)
        );
    }
    public BLLTest(){

    }



    @ParameterizedTest
    public void addTest(){

    }
}
