package br.furb.problema02.factories;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.order.IOrderType;

class OrderTypeFactoryTest {

    @Test
    void shouldCreateBuyOrder() {
        IOrderType order = OrderTypeFactory.createOrder(
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
        IOrderType order = OrderTypeFactory.createOrder(
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
    void shouldThrowExceptionWhenInvestorNameIsInvalid() {
        assertAll(
            () -> assertEquals(
                "Nome do investidor inválido",
                assertThrows(
                    IllegalArgumentException.class,
                    () -> OrderTypeFactory.createOrder(null, new BigDecimal("20.00"), OrderTypeEnum.BUY)
                ).getMessage()
            ),
            () -> assertEquals(
                "Nome do investidor inválido",
                assertThrows(
                    IllegalArgumentException.class,
                    () -> OrderTypeFactory.createOrder("   ", new BigDecimal("20.00"), OrderTypeEnum.BUY)
                ).getMessage()
            )
        );
    }

    @Test
    void shouldThrowExceptionWhenOrderValueIsInvalid() {
        assertAll(
            () -> assertEquals(
                "Valor da ordem inválido",
                assertThrows(
                    IllegalArgumentException.class,
                    () -> OrderTypeFactory.createOrder("Joaquim", null, OrderTypeEnum.BUY)
                ).getMessage()
            ),
            () -> assertEquals(
                "Valor da ordem inválido",
                assertThrows(
                    IllegalArgumentException.class,
                    () -> OrderTypeFactory.createOrder("Joaquim", BigDecimal.ZERO, OrderTypeEnum.BUY)
                ).getMessage()
            ),
            () -> assertEquals(
                "Valor da ordem inválido",
                assertThrows(
                    IllegalArgumentException.class,
                    () -> OrderTypeFactory.createOrder("Joaquim", new BigDecimal("-1.00"), OrderTypeEnum.BUY)
                ).getMessage()
            )
        );
    }

    @Test
    void shouldThrowExceptionWhenOrderTypeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            OrderTypeFactory.createOrder(
                "Joaquim",
                new BigDecimal("20.00"),
                null
            );
        });
    }
}
