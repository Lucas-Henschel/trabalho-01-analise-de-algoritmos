package br.furb.problema02.model.stock;

import java.math.BigDecimal;

public class StockInfo {
    private String name;
    private BigDecimal value;

    public StockInfo(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }
}
