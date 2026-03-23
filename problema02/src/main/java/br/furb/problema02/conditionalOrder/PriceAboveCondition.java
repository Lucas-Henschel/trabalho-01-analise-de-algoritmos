package br.furb.problema02.conditionalOrder;

import java.math.BigDecimal;

import br.furb.problema02.enums.CompareResultsEnum;

public class PriceAboveCondition implements ICondition {
    private final BigDecimal target;

    public PriceAboveCondition(BigDecimal target) {
        this.target = target;
    }

    @Override
    public boolean verifyCondition(BigDecimal stockValue) {
        return stockValue.compareTo(target) >= CompareResultsEnum.EQUALITY_RESULT.getValue();
    }
}
