package br.furb.problema02.factories;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.order.IOrderType;
import br.furb.problema02.order.OrderTypeBuy;
import br.furb.problema02.order.OrderTypeSell;

import java.math.BigDecimal;

public class OrderTypeFactory {
    public static IOrderType createOrder(String investorName, BigDecimal orderValue, OrderTypeEnum orderType) throws IllegalArgumentException {
        if (investorName == null || investorName.isBlank()) {
            throw new IllegalArgumentException("Nome do investidor inválido");
        }

        if (orderValue == null || orderValue.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor da ordem inválido");
        }

        if (orderType == null) {
            throw new IllegalArgumentException("Tipo de ordem inválido!");
        }

        return switch (orderType) {
            case BUY -> new OrderTypeBuy(investorName, orderValue);
            case SELL -> new OrderTypeSell(investorName, orderValue);
        };
    }
}
