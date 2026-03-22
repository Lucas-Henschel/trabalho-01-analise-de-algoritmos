package br.furb.problema02.service;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.factories.OrderTypeFactory;
import br.furb.problema02.model.Investor;
import br.furb.problema02.model.Orders;
import br.furb.problema02.model.stock.Stock;
import br.furb.problema02.observer.Observer;
import br.furb.problema02.order.IOrderType;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class StockStateTest {

    private static final String STOCK_NAME = "PETR4";
    private static final BigDecimal STOCK_PRICE = new BigDecimal("30.00");
    private static final String DUPLICATE_OBSERVER_ERROR = "Investidor já está observando a ação";

    private StockState stockState;
    private Stock stock;
    private Investor investor;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        stockState = new StockState();
        stock = new Stock(STOCK_NAME, STOCK_PRICE);
        investor = new Investor("João");
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void shouldInitializeWithEmptyOrders() {
        Orders orders = stockState.getOrders();

        assertNotNull(orders);
        assertTrue(orders.isEmpty());
    }

    @Test
    void shouldRegisterObserver() {
        Observer observer = investor;
        assertDoesNotThrow(() -> stockState.registerObserverStocks(observer));
    }

    @Test
    void shouldThrowExceptionWhenRegisteringDuplicateObserver() {
        Observer observer = investor;
        stockState.registerObserverStocks(observer);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> stockState.registerObserverStocks(observer)
        );

        assertEquals(DUPLICATE_OBSERVER_ERROR, exception.getMessage());
    }

    @Test
    void shouldNotifyRegisteredObservers() {
        Observer observer = investor;

        stockState.registerObserverStocks(observer);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            stockState.notifyObservers(stock);
            String output = outputStream.toString();

            assertTrue(output.contains("João"));
            assertTrue(output.contains(STOCK_NAME));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void shouldNotifyMultipleObservers() {
        Observer observer1 = investor;
        Observer observer2 = new Investor("Maria");

        stockState.registerObserverStocks(observer1);
        stockState.registerObserverStocks(observer2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            stockState.notifyObservers(stock);
            String output = outputStream.toString();

            assertTrue(output.contains("João"));
            assertTrue(output.contains("Maria"));
        } finally {
            System.setOut(originalOut);
        }
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
    void shouldNotifyWithoutObservers() {
        assertDoesNotThrow(() -> stockState.notifyObservers(stock));
        assertDoesNotThrow(() -> stockState.notifyObservers(stock));
    }
}
