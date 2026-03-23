package br.furb.problema02.conditionalOrder;

import java.math.BigDecimal;

import br.furb.problema02.enums.CompareResultsEnum;

public class PriceAboveCondition implements ICondition {
    private final BigDecimal target;

    public PriceAboveCondition(BigDecimal target) {
        if (target == null || target.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor alvo inválido");
        }

        this.target = target;
    }

    @Override
    public boolean verifyCondition(BigDecimal stockValue) {
        return stockValue.compareTo(target) >= CompareResultsEnum.EQUALITY_RESULT.getValue();
    }
}
