package com.georgiana.pricecomparatormarket.model;

import java.time.LocalDate;

public class Discount {
    private String productId, productName, brand, packageUnit, productCategory;
    private LocalDate fromDate, toDate;
    private double packageQuantity, percentageOfDiscount;

    public Discount( String productId, String productName, String brand, double packageQuantity, String packageUnit, String productCategory, LocalDate fromDate, LocalDate toDate, double percentageOfDiscount){
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.packageQuantity = packageQuantity;
        this.packageUnit = packageUnit;
        this.productCategory = productCategory;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.percentageOfDiscount = percentageOfDiscount;
    }


    public String getProductId() {return productId; }
    public String getProductName() { return productName; }
    public String getBrand() { return brand; }
    public double getPercentageOfDiscount() { return percentageOfDiscount; }
    public LocalDate getFromDate() { return  fromDate;}
}
