package br.furb.problema02.model;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.factories.OrderTypeFactory;
import br.furb.problema02.model.order.Orders;
import br.furb.problema02.order.IOrderType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrdersTest {

    @Test
    void shouldRemoveExistingOrderAndBecomeEmptyAgain() {
        Orders orders = new Orders();
        IOrderType buyOrder = OrderTypeFactory.createOrder("Marina Costa", new BigDecimal("1250.75"), OrderTypeEnum.BUY);

        orders.add(buyOrder);
        orders.remove(buyOrder);

        assertTrue(orders.isEmpty());
        assertEquals(0, orders.size());
    }

    @Test
    void shouldThrowExceptionWhenAddingNullOrder() {
        Orders orders = new Orders();

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> orders.add(null)
        );

        assertEquals("Ordem inválida", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRemovingNullOrder() {
        Orders orders = new Orders();

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> orders.remove(null)
        );

        assertEquals("Ordem inválida", exception.getMessage());
    }

    @Test
    void shouldFindMatchingOrderByTypeAndValue() {
        Orders orders = new Orders();
        IOrderType sellOrder = OrderTypeFactory.createOrder("Lucas Almeida", new BigDecimal("24.00"), OrderTypeEnum.SELL);

        orders.add(sellOrder);

        assertTrue(orders.findByTypeAndValue(OrderTypeEnum.SELL, new BigDecimal("24.0")).isPresent());
        assertFalse(orders.findByTypeAndValue(OrderTypeEnum.BUY, new BigDecimal("24.0")).isPresent());
    }
}
