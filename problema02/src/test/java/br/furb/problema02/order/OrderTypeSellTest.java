package br.furb.problema02.order;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.factories.OrderTypeFactory;

class OrderTypeSellTest {

    @Test
    void shouldReturnCorrectType() {
        IOrderType order = OrderTypeFactory.createOrder(
            "Mariana",
            new BigDecimal("30.50"),
            OrderTypeEnum.SELL
        );

        assertEquals(OrderTypeEnum.SELL, order.getOrderType());
    }

    @Test
    void shouldReturnCorrectData() {
        IOrderType order = OrderTypeFactory.createOrder(
            "Mariana",
            new BigDecimal("30.00"),
            OrderTypeEnum.SELL
        );

        assertEquals("Mariana", order.getInvestorName());
        assertEquals(new BigDecimal("30.00"), order.getOrderValue());
    }
}
