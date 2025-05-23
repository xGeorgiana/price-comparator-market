package com.georgiana.pricecomparatormarket.model;

public class Product {
    private String productId, productName, productCategory, brand, packageUnit, currency;
    private double packageQuantity, price;

    public Product(String productId, String productName, String productCategory, String brand, double packageQuantity, String packageUnit, double price, String currency){
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.brand = brand;
        this.packageQuantity = packageQuantity;
        this.packageUnit = packageUnit;
        this.price = price;
        this.currency = currency;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getProductCategory() { return productCategory; }
    public String getBrand() { return brand; }
    public String getPackageUnit() { return packageUnit; }
    public String getCurrency() { return currency; }
    public double getPackageQuantity() { return packageQuantity; }
    public double getPrice() { return price; }
}
