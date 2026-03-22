package br.furb.factories;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.factories.OrderTypeFactory;
import br.furb.problema02.order.IOrderType;

class OrderTypeFactoryTest {

	@Test
    void shouldCreateBuyOrder() {
        IOrderType order = OrderTypeFactory.CreateOrder(
                "Joaquim",
                new BigDecimal("24.00"),
                OrderTypeEnum.BUY
        );

        assertNotNull(order);
        assertEquals("Joaquim", order.getInvestorName());
        assertEquals(new BigDecimal("24.00"), order.getOrderValue());
        assertEquals(OrderTypeEnum.BUY, order.getOrderType());
    }

    @Test
    void shouldCreateSellOrder() {
        IOrderType order = OrderTypeFactory.CreateOrder(
                "Mariana",
                new BigDecimal("30.50"),
                OrderTypeEnum.SELL
        );

        assertNotNull(order);
        assertEquals("Mariana", order.getInvestorName());
        assertEquals(new BigDecimal("30.50"), order.getOrderValue());
        assertEquals(OrderTypeEnum.SELL, order.getOrderType());
    }

    @Test
    void shouldThrowExceptionWhenOrderTypeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            OrderTypeFactory.CreateOrder(
                    "Joaquim",
                    new BigDecimal("20.00"),
                    null
            );
        });
    }
}
