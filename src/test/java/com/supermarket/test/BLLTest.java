package com.supermarket.test;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;

import com.supermarket.BLL.AccountBLL;
import com.supermarket.BLL.BrandBLL;
import com.supermarket.BLL.CategoryBLL;
import com.supermarket.BLL.CustomerBLL;
import com.supermarket.BLL.DecentralizationBLL;
import com.supermarket.BLL.DiscountBLL;
import com.supermarket.BLL.Discount_detailBLL;
import com.supermarket.BLL.Export_detailBLL;
import com.supermarket.BLL.Export_noteBLL;
import com.supermarket.BLL.FunctionBLL;
import com.supermarket.BLL.Import_noteBLL;
import com.supermarket.BLL.ModuleBLL;
import com.supermarket.BLL.ProductBLL;
import com.supermarket.BLL.PromotionBLL;
import com.supermarket.BLL.Promotion_giftBLL;
import com.supermarket.BLL.Promotion_itemBLL;
import com.supermarket.BLL.ReceiptBLL;
import com.supermarket.BLL.Receipt_detailBLL;
import com.supermarket.BLL.RoleBLL;
import com.supermarket.BLL.ShipmentBLL;
import com.supermarket.BLL.StaffBLL;
import com.supermarket.BLL.StatisticBLL;
import com.supermarket.BLL.SupplierBLL;
import com.supermarket.DTO.Account;
import com.supermarket.DTO.Brand;
import com.supermarket.DTO.Category;
import com.supermarket.DTO.Customer;
import com.supermarket.DTO.Decentralization;
import com.supermarket.DTO.Discount;
import com.supermarket.DTO.Discount_detail;
import com.supermarket.DTO.Export;
import com.supermarket.DTO.Export_detail;
import com.supermarket.DTO.Function;
import com.supermarket.DTO.Import;
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
        new TestClass(ProductBLL.class,
        new TestClass(Promotion_giftBLL.class,
        new TestClass(Promotion_itemBLL.class,
        new TestClass(PromotionBLL.class,
        new TestClass(Receipt_detailBLL.class,
        new TestClass(ReceiptBLL.class,
        new TestClass(RoleBLL.class,
        new TestClass(ShipmentBLL.class,
        new TestClass(StaffBLL.class,
        new TestClass(StatisticBLL.class,
        new TestClass(RoleBLL.class,
        new TestClass(StatisticBLL.class,
        new TestClass(SupplierBLL.class
        );
    }
    public BLLTest(){

    }



    @ParameterizedTest
    public void addTest(){

    }
}
