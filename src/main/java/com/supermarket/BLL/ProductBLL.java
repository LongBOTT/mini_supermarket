package com.supermarket.BLL;

import com.supermarket.DAL.ProductDAL;
import com.supermarket.DTO.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductBLL extends Manager<Product> {
    private ProductDAL productDAL;
    private List<Product> productList;

    public ProductBLL() {
        productDAL = new ProductDAL();
        productList = searchProducts("deleted = 0");
    }

    public ProductDAL getProductDAL() {
        return productDAL;
    }

    public void setProductDAL(ProductDAL productDAL) {
        this.productDAL = productDAL;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Object[][] getData() {
        return getData(productList);
    }

    public boolean addProduct(Product product) {
        productList.add(product);
        return productDAL.addProduct(product) != 0;
    }

    public boolean updateProduct(Product product) {
        productList.set(getIndex(product, "id", productList), product);
        return productDAL.updateProduct(product) != 0;
    }

    public boolean deleteProduct(Product product) {
        productList.remove(getIndex(product, "id", productList));
        return productDAL.deleteProduct("id = " + product.getId()) != 0;
    }

    public List<Product> searchProducts(String... conditions) {
        return productDAL.searchProducts(conditions);
    }

    public List<Product> findProducts(String key, String value) {
        List<Product> list = new ArrayList<>();
        for (Product product : productList) {
            if (getValueByKey(product, key).toString().toLowerCase().contains(value.toLowerCase())) {
                list.add(product);
            }
        }
        return list;
    }

    public List<Product> findProductsBy(Map<String, Object> conditions) {
        List<Product> products = productList;
        for (Map.Entry<String, Object> entry : conditions.entrySet())
            products = findObjectsBy(entry.getKey(), entry.getValue(), products);
        return products;
    }

    public boolean exists(Product product) {
        return !findProductsBy(Map.of(
            "name", product.getName(),
            "brand_id", product.getBrand_id(),
            "category_id", product.getCategory_id(),
            "barcode",product.getBarcode()
        )).isEmpty();
    }

    public boolean exists(Map<String, Object> conditions) {
        return !findProductsBy(conditions).isEmpty();
    }

    @Override
    public Object getValueByKey(Product product, String key) {
        return switch (key) {
            case "id" -> product.getId();
            case "name" -> product.getName();
            case "brand_id" -> product.getBrand_id();
            case "category_id" -> product.getCategory_id();
            case "unit" -> product.getUnit();
            case "cost" -> product.getCost();
            case "quantity" -> product.getQuantity();
            case "image" -> product.getImage();
            case "barcode" -> product.getBarcode();
            default -> null;
        };
    }
}
