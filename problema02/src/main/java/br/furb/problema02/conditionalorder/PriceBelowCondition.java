package br.furb.problema02.conditionalorder;

import java.math.BigDecimal;

import br.furb.problema02.enums.CompareResultsEnum;

public class PriceBelowCondition implements ICondition {

    private final BigDecimal target;

    public PriceBelowCondition(BigDecimal target) {
        this.target = target;
    }

    @Override
    public boolean verifyCondition(BigDecimal stockValue) {
        return stockValue.compareTo(target) <= CompareResultsEnum.ZERO.getValue();
    }
}
