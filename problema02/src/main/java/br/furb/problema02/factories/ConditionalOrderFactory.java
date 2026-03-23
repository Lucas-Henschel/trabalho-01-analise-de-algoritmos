package br.furb.problema02.factories;

import java.math.BigDecimal;

import br.furb.problema02.conditionalorder.ConditionalOrder;
import br.furb.problema02.conditionalorder.ICondition;
import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.order.IOrderType;

public class ConditionalOrderFactory {

    public static ConditionalOrder create(
            String investorName,
            BigDecimal orderValue,
            OrderTypeEnum type,
            ICondition condition) {

        IOrderType order = OrderTypeFactory.createOrder(
                investorName,
                orderValue,
                type);

        return new ConditionalOrder(order, condition);
    }
}
