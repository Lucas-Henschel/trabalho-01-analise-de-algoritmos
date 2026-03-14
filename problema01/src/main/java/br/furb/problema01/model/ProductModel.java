package br.furb.problema01.model;

import java.math.BigDecimal;

public class ProductModel {
    private String name;
    private BigDecimal price;
    private int weight;

    public ProductModel(String name, BigDecimal price, int weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }
}
