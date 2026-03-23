package br.furb.problema02.service;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.factories.OrderTypeFactory;
import br.furb.problema02.model.Investor;
import br.furb.problema02.model.TradeResult;
import br.furb.problema02.model.stock.Stock;
import br.furb.problema02.model.stock.StockInfo;
import br.furb.problema02.order.IOrderType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TradeExecutorTest {

    private static final String STOCK_NAME = "PETR4";
    private static final BigDecimal INITIAL_PRICE = new BigDecimal("30.00");
    private static final BigDecimal MATCH_PRICE = new BigDecimal("31.00");

    private StockInfo stockInfo;
    private StockState stockState;
    private TradeExecutor tradeExecutor;
    private Stock stock;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        stockInfo = new StockInfo(STOCK_NAME, INITIAL_PRICE);
        stockState = new StockState();
        tradeExecutor = new TradeExecutor(stockInfo, stockState);
        stock = new Stock(STOCK_NAME, INITIAL_PRICE);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void shouldCreateTradeExecutor() {
        assertNotNull(tradeExecutor);
    }

    @Test
    void shouldAddPendingOrderWhenNoMatch() {
        IOrderType buyOrder = OrderTypeFactory.createOrder(
            "João",
            INITIAL_PRICE,
            OrderTypeEnum.BUY
        );

        TradeResult result = tradeExecutor.processOrder(stock, buyOrder, OrderTypeEnum.BUY);

        assertTrue(result.isPending());
        assertFalse(result.hasMatch());
        assertEquals(1, stockState.getOrders().size());
    }

    @Test
    void shouldMatchOrdersWhenPriceMatches() {
        IOrderType buyOrder = OrderTypeFactory.createOrder(
            "João",
            MATCH_PRICE,
            OrderTypeEnum.BUY
        );

        IOrderType sellOrder = OrderTypeFactory.createOrder(
            "Maria",
            MATCH_PRICE,
            OrderTypeEnum.SELL
        );

        stockState.getOrders().add(buyOrder);

        TradeResult result = tradeExecutor.processOrder(stock, sellOrder, OrderTypeEnum.SELL);

        assertTrue(result.hasMatch());
        assertFalse(result.isPending());
        assertEquals(MATCH_PRICE, result.getNegotiatedValue().orElse(null));
        assertTrue(stockState.getOrders().isEmpty());
    }

    @Test
    void shouldUpdateStockValueAfterMatch() {
        IOrderType sellOrder = OrderTypeFactory.createOrder(
            "João",
            MATCH_PRICE,
            OrderTypeEnum.SELL
        );

        IOrderType buyOrder = OrderTypeFactory.createOrder(
            "Maria",
            MATCH_PRICE,
            OrderTypeEnum.BUY
        );

        stockState.getOrders().add(sellOrder);
        BigDecimal initialValue = stockInfo.getValue();
        assertEquals(INITIAL_PRICE, initialValue);
        tradeExecutor.processOrder(stock, buyOrder, OrderTypeEnum.BUY);

        assertEquals(MATCH_PRICE, stockInfo.getValue());
    }

    @Test
    void shouldNotifyObserversAfterMatch() {
        Investor observer = new Investor("João");
        stock.registerObserver(observer);

        IOrderType sellOrder = OrderTypeFactory.createOrder(
            "João",
            MATCH_PRICE,
            OrderTypeEnum.SELL
        );

        IOrderType buyOrder = OrderTypeFactory.createOrder(
            "Maria",
            MATCH_PRICE,
            OrderTypeEnum.BUY
        );

        stockState.getOrders().add(sellOrder);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            tradeExecutor.processOrder(stock, buyOrder, OrderTypeEnum.BUY);
            String output = outputStream.toString();

            assertTrue(output.contains("João"));
            assertTrue(output.contains(STOCK_NAME));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void shouldRemoveBothOrdersAfterMatch() {
        IOrderType buyOrder = OrderTypeFactory.createOrder(
            "João",
            MATCH_PRICE,
            OrderTypeEnum.BUY
        );

        IOrderType sellOrder = OrderTypeFactory.createOrder(
            "Maria",
            MATCH_PRICE,
            OrderTypeEnum.SELL
        );

        stockState.getOrders().add(buyOrder);
        assertEquals(1, stockState.getOrders().size());

        tradeExecutor.processOrder(stock, sellOrder, OrderTypeEnum.SELL);

        assertTrue(stockState.getOrders().isEmpty());
    }

    @Test
    void shouldNotMatchOrdersWithDifferentPrices() {
        IOrderType buyOrder = OrderTypeFactory.createOrder(
            "João",
            INITIAL_PRICE,
            OrderTypeEnum.BUY
        );

        IOrderType sellOrder = OrderTypeFactory.createOrder(
            "Maria",
            MATCH_PRICE,
            OrderTypeEnum.SELL
        );

        stockState.getOrders().add(buyOrder);
        TradeResult result = tradeExecutor.processOrder(stock, sellOrder, OrderTypeEnum.SELL);

        assertTrue(result.isPending());
        assertFalse(result.hasMatch());
        assertEquals(2, stockState.getOrders().size());
    }

    @Test
    void shouldExecuteMatchCorrectly() {
        IOrderType buyOrder = OrderTypeFactory.createOrder(
            "João",
            MATCH_PRICE,
            OrderTypeEnum.BUY
        );

        IOrderType sellOrder = OrderTypeFactory.createOrder(
            "Maria",
            MATCH_PRICE,
            OrderTypeEnum.SELL
        );

        stockState.getOrders().add(buyOrder);
        stockState.getOrders().add(sellOrder);

        TradeResult result = tradeExecutor.executeMatch(stock, buyOrder, sellOrder);

        assertTrue(result.hasMatch());
        assertEquals(MATCH_PRICE, result.getNegotiatedValue().orElse(null));
        assertEquals(MATCH_PRICE, stockInfo.getValue());
        assertTrue(stockState.getOrders().isEmpty());
    }

    @Test
    void shouldHandleMultiplePendingOrders() {
        IOrderType buyOrder1 = OrderTypeFactory.createOrder(
            "João",
            INITIAL_PRICE,
            OrderTypeEnum.BUY
        );

        IOrderType buyOrder2 = OrderTypeFactory.createOrder(
            "Maria",
            MATCH_PRICE,
            OrderTypeEnum.BUY
        );

        tradeExecutor.processOrder(stock, buyOrder1, OrderTypeEnum.BUY);
        tradeExecutor.processOrder(stock, buyOrder2, OrderTypeEnum.BUY);

        assertEquals(2, stockState.getOrders().size());
    }
}
