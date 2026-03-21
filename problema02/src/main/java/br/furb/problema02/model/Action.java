package br.furb.problema02.model;

import java.math.BigDecimal;

public class Action {
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

    public Action(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }
}
