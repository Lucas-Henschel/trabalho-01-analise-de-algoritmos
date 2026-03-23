package br.furb.problema02.service;

import br.furb.problema02.conditionalOrder.ConditionalOrder;
import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.factories.ConditionalOrderFactory;
import br.furb.problema02.factories.OrderTypeFactory;
import br.furb.problema02.model.Orders;
import br.furb.problema02.order.IOrderType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StockStateTest {

    private static final BigDecimal STOCK_PRICE = new BigDecimal("30.00");

    private StockState stockState;

    @BeforeEach
    void setUp() {
        stockState = new StockState();
    }

    @Test
    void shouldInitializeWithEmptyOrders() {
        Orders orders = stockState.getOrders();

        assertNotNull(orders);
        assertTrue(orders.isEmpty());
    }

    @Test
    void shouldMaintainOrdersAfterRegistration() {
        Orders orders = stockState.getOrders();

        IOrderType order = OrderTypeFactory.createOrder(
            "Investidor",
            STOCK_PRICE,
            OrderTypeEnum.BUY
        );

        orders.add(order);

        assertEquals(1, stockState.getOrders().size());
        assertFalse(stockState.getOrders().isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenAddingNullConditionalOrder() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> stockState.addConditionalOrder(null)
        );

        assertEquals("Ordem inválida", exception.getMessage());
    }

    @Test
    void shouldMaintainConditionalOrdersAfterAddition() {
        ConditionalOrder conditionalOrder = ConditionalOrderFactory.create(
            "Investidor",
            STOCK_PRICE,
            OrderTypeEnum.BUY,
            stockValue -> true
        );

        stockState.addConditionalOrder(conditionalOrder);

        assertEquals(1, stockState.getConditionalOrders().size());
    }
}
