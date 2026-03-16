package br.furb.problema01.model;

import java.math.BigDecimal;

public class ProductDetails {

    private BigDecimal price;
    private Weight weight;

    public ProductDetails(BigDecimal price, int weightInGrams) {
        this.price = price;
        this.weight = new Weight(weightInGrams);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getWeightInGrams() {
        return weight.inGrams();
    }
}