package br.furb.problema02.model.stock;

import java.math.BigDecimal;

public class StockInfo {
    private final String name;
    private BigDecimal value;

    public StockInfo(String name, BigDecimal value) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome da ação inválido");
        }

        validateValue(value);

        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        validateValue(value);
        this.value = value;
    }

    private void validateValue(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor da ação inválido");
        }
    }
}
