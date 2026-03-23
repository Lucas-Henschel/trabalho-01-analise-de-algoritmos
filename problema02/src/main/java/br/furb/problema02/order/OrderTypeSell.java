package br.furb.problema02.order;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.Order;

import java.math.BigDecimal;

public class OrderTypeSell extends Order {
    public OrderTypeSell(String investorName, BigDecimal orderValue) {
        super(investorName, orderValue);
    }

    public OrderTypeEnum getOrderType() {
        return OrderTypeEnum.SELL;
    }
}
