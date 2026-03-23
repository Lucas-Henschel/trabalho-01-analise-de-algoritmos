package br.furb.problema02.model;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.investor.Investor;
import br.furb.problema02.model.stock.Stock;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
    private static final String OBSERVER_NAME = "João";

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
    void shouldThrowExceptionWhenCreatingStockWithNullName() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Stock(null, STOCK_VALUE)
        );

        assertEquals("Nome da ação inválido", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingStockWithBlankName() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Stock("   ", STOCK_VALUE)
        );

        assertEquals("Nome da ação inválido", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingStockWithNullValue() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Stock(STOCK_NAME, null)
        );

        assertEquals("Valor da ação inválido", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingStockWithZeroValue() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Stock(STOCK_NAME, BigDecimal.ZERO)
        );

        assertEquals("Valor da ação inválido", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingStockWithNegativeValue() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Stock(STOCK_NAME, new BigDecimal("-1.00"))
        );

        assertEquals("Valor da ação inválido", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingOrderWithNullInvestorName() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> stock.placeOrder(null, MATCH_VALUE, OrderTypeEnum.BUY)
        );

        assertEquals("Nome do investidor inválido", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingOrderWithBlankInvestorName() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> stock.placeOrder("   ", MATCH_VALUE, OrderTypeEnum.BUY)
        );

        assertEquals("Nome do investidor inválido", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingOrderWithNullValue() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> stock.placeOrder(BUY_INVESTOR, null, OrderTypeEnum.BUY)
        );

        assertEquals("Valor da ordem inválido", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingOrderWithZeroValue() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> stock.placeOrder(BUY_INVESTOR, BigDecimal.ZERO, OrderTypeEnum.BUY)
        );

        assertEquals("Valor da ordem inválido", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingOrderWithNegativeValue() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> stock.placeOrder(BUY_INVESTOR, new BigDecimal("-1.00"), OrderTypeEnum.BUY)
        );

        assertEquals("Valor da ordem inválido", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRegisteringNullObserver() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> stock.registerObserver(null)
        );

        assertEquals("Observador inválido", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRemovingNullObserver() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> stock.removeObserver(null)
        );

        assertEquals("Observador inválido", exception.getMessage());
    }

    @Test
    void shouldRegisterObserverWithoutThrowingException() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);
        Investor observer = new Investor(OBSERVER_NAME);

        assertDoesNotThrow(() -> stock.registerObserver(observer));
    }

    @Test
    void shouldThrowExceptionWhenRegisteringDuplicateObserverOnStock() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);
        Investor observer = new Investor(OBSERVER_NAME);
        stock.registerObserver(observer);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> stock.registerObserver(observer)
        );

        assertEquals("Investidor já está observando a ação", exception.getMessage());
    }

    @Test
    void shouldNotifyRegisteredObserversThroughStock() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);
        Investor observer = new Investor(OBSERVER_NAME);
        stock.registerObserver(observer);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            stock.notifyObservers();
            String output = outputStream.toString();

            assertTrue(output.contains(OBSERVER_NAME));
            assertTrue(output.contains(STOCK_NAME));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void shouldNotNotifyRemovedObserver() {
        Stock stock = new Stock(STOCK_NAME, STOCK_VALUE);
        Investor observer = new Investor(OBSERVER_NAME);
        stock.registerObserver(observer);
        stock.removeObserver(observer);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            stock.notifyObservers();
            String output = outputStream.toString();

            assertTrue(output.isEmpty());
        } finally {
            System.setOut(originalOut);
        }
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
