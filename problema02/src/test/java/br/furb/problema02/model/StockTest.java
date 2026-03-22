package br.furb.problema02.model;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.stock.Stock;
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
    private static final BigDecimal MATCH_VALUE = new BigDecimal("24.00");

    @Test
    void shouldInitializeStockWithProvidedNameValueAndEmptyOrders() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);

        assertEquals(STOCK_NAME, stock.getName());
        assertEquals(STOCK_VALUE, stock.getValue());
        assertFalse(stock.hasPendingOrders());
        assertEquals(0, stock.pendingOrdersCount());
    }

    @Test
    void shouldKeepBuyOrderPendingWhenThereIsNoMatchingOrder() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);

        TradeResult result = stock.placeOrder(BUY_INVESTOR, new BigDecimal("1250.75"), OrderTypeEnum.BUY);

        assertTrue(result.isPending());
        assertFalse(result.hasMatch());
        assertFalse(result.getMatchedOrder().isPresent());
        assertFalse(result.getNegotiatedValue().isPresent());
        assertTrue(stock.hasPendingOrders());
        assertEquals(1, stock.pendingOrdersCount());
    }

    @Test
    void shouldKeepSellOrderPendingWhenThereIsNoMatchingOrder() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);

        TradeResult result = stock.placeOrder(SELL_INVESTOR, new BigDecimal("980.30"), OrderTypeEnum.SELL);

        assertTrue(result.isPending());
        assertFalse(result.hasMatch());
        assertFalse(result.getMatchedOrder().isPresent());
        assertFalse(result.getNegotiatedValue().isPresent());
        assertTrue(stock.hasPendingOrders());
        assertEquals(1, stock.pendingOrdersCount());
    }

    @Test
    void shouldThrowExceptionWhenCreatingOrderWithNullType() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> stock.placeOrder(BUY_INVESTOR, new BigDecimal("1250.75"), null)
        );

        assertEquals("Tipo de ordem inválido!", exception.getMessage());
    }

    @Test
    void shouldKeepOrderPendingWhenInvestorRegistersWithoutMatchingOrder() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);
        Investor investor = new Investor(BUY_INVESTOR);

        TradeResult result = investor.orderRegister(stock, MATCH_VALUE, OrderTypeEnum.BUY);

        assertTrue(result.isPending());
        assertFalse(result.hasMatch());
        assertFalse(result.getMatchedOrder().isPresent());
        assertFalse(result.getNegotiatedValue().isPresent());
        assertTrue(stock.hasPendingOrders());
        assertEquals(1, stock.pendingOrdersCount());
        assertEquals(STOCK_VALUE, stock.getValue());
    }

    @Test
    void shouldMatchOrdersRemoveBothAndUpdateStockValue() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);
        Investor buyInvestor = new Investor(BUY_INVESTOR);
        Investor sellInvestor = new Investor(SELL_INVESTOR);

        buyInvestor.orderRegister(stock, MATCH_VALUE, OrderTypeEnum.BUY);
        TradeResult result = sellInvestor.orderRegister(stock, new BigDecimal("24.0"), OrderTypeEnum.SELL);

        assertTrue(result.hasMatch());
        assertFalse(result.isPending());
        assertTrue(result.getMatchedOrder().isPresent());
        assertTrue(result.getNegotiatedValue().isPresent());
        assertEquals(MATCH_VALUE, result.getNegotiatedValue().orElseThrow());
        assertFalse(stock.hasPendingOrders());
        assertEquals(0, stock.pendingOrdersCount());
        assertEquals(MATCH_VALUE, stock.getValue());
    }
}
