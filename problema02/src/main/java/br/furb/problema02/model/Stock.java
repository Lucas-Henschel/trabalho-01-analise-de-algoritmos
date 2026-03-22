package br.furb.problema02.model;

import java.math.BigDecimal;

public class Stock {
    private String name;
    private BigDecimal value;
    private final Orders orders = new Orders();

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Orders getOrders() {
        return orders;
    }

    public Stock(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }
}
