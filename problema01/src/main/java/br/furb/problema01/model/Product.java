package br.furb.problema01.model;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;
    private double weight;

    public Product() {
    }

    public Product(String name, BigDecimal price, double weight) {
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

    public double getWeight() {
        return weight;
    }
}
