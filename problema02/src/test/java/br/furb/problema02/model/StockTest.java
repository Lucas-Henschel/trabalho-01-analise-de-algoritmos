package br.furb.problema02.model;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.factories.OrderTypeFactory;
import br.furb.problema02.model.stock.Stock;
import br.furb.problema02.order.IOrderType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StockTest {

    private static final String STOCK_NAME = "PETR4 - Petrobras";
    private static final BigDecimal STOCK_VALUE = new BigDecimal("37.85");
    private static final String BUY_INVESTOR = "Marina Costa";
    private static final String SELL_INVESTOR = "Lucas Almeida";

    @Test
    void shouldInitializeStockWithProvidedNameValueAndEmptyOrders() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);

        assertEquals(STOCK_NAME, stock.getName());
        assertEquals(STOCK_VALUE, stock.getValue());
        assertTrue(stock.getOrders().isEmpty());
    }

    @Test
    void shouldAddBuyOrderToStock() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);

        stock.getOrders().add(OrderTypeFactory.CreateOrder(BUY_INVESTOR, new BigDecimal("1250.75"), OrderTypeEnum.BUY));

        assertFalse(stock.getOrders().isEmpty());
    }

    @Test
    void shouldAddSellOrderToStock() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);

        stock.getOrders().add(OrderTypeFactory.CreateOrder(SELL_INVESTOR, new BigDecimal("980.30"), OrderTypeEnum.SELL));

        assertFalse(stock.getOrders().isEmpty());
    }

    @Test
    void shouldRemoveExistingOrderAndBecomeEmptyAgain() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);
        IOrderType buyOrder = OrderTypeFactory.CreateOrder(BUY_INVESTOR, new BigDecimal("1250.75"), OrderTypeEnum.BUY);

        stock.getOrders().add(buyOrder);
        stock.getOrders().remove(buyOrder);

        assertTrue(stock.getOrders().isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenAddingNullOrder() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stock.getOrders().add(null));

        assertEquals("Ordem inválida", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRemovingNullOrder() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stock.getOrders().remove(null));

        assertEquals("Ordem inválida", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingOrderWithNullType() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> OrderTypeFactory.CreateOrder(BUY_INVESTOR, new BigDecimal("1250.75"), null));

        assertEquals("Tipo de ordem inválido!", exception.getMessage());
    }
}