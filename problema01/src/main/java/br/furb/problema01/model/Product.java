package br.furb.problema01.model;

import java.math.BigDecimal;

public class Product {
    private String name;
    private ProductDetails productDetails;

    public Product(String name, BigDecimal price, int weightInGrams) {
        this.name = name;
        this.productDetails = new ProductDetails(price, weightInGrams);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return productDetails.getPrice();
    }

    public int getWeightInGrams() {
        return productDetails.getWeightInGrams();
    }
}
