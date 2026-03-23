package br.furb.problema02.conditionalorder;

import java.math.BigDecimal;

import br.furb.problema02.order.IOrderType;

public class ConditionalOrder {

    private final IOrderType order;
    private final ICondition condition;

    public ConditionalOrder(IOrderType order, ICondition condition) {
        if (order == null || condition == null) {
            throw new IllegalArgumentException("Dados inválidos");
        }

        this.order = order;
        this.condition = condition;
    }

    public boolean shouldExecute(BigDecimal stockValue) {
        return condition.verifyCondition(stockValue);
    }

    public IOrderType getOrder() {
        return order;
    }
}