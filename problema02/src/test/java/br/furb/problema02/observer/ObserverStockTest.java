package br.furb.problema02.observer;

import br.furb.problema02.model.Investor;
import br.furb.problema02.model.stock.Stock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObserverStockTest {

    private static final String STOCK_NAME = "PETR4";
    private static final BigDecimal STOCK_PRICE = new BigDecimal("30.00");
    private static final String DUPLICATE_OBSERVER_ERROR = "Investidor já está observando a ação";
    private static final String INVESTOR_NAME = "João Silva";

    private ObserverStock observerStock;
    private Stock stock;
    private Investor investor;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        observerStock = new ObserverStock();
        stock = new Stock(STOCK_NAME, STOCK_PRICE);
        investor = new Investor(INVESTOR_NAME);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void shouldRegisterObserver() {
        Observer observer = investor;
        assertDoesNotThrow(() -> observerStock.register(observer));
    }

    @Test
    void shouldThrowExceptionWhenRegisteringNullObserver() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> observerStock.register(null)
        );

        assertEquals("Observador inválido", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRemovingNullObserver() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> observerStock.remove(null)
        );

        assertEquals("Observador inválido", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRegisteringDuplicateObserver() {
        Observer observer = investor;
        observerStock.register(observer);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> observerStock.register(observer)
        );

        assertEquals(DUPLICATE_OBSERVER_ERROR, exception.getMessage());
    }

    @Test
    void shouldNotifyAllRegisteredObservers() {
        Observer observer1 = investor;
        Observer observer2 = new Investor("Maria");

        observerStock.register(observer1);
        observerStock.register(observer2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            observerStock.notifyObservers(stock);
            String output = outputStream.toString();

            assertTrue(output.contains(INVESTOR_NAME));
            assertTrue(output.contains("Maria"));
            assertTrue(output.contains(STOCK_NAME));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void shouldNotifyWithCorrectStockInformation() {
        Observer observer = investor;

        observerStock.register(observer);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            observerStock.notifyObservers(stock);
            String output = outputStream.toString();

            assertTrue(output.contains(INVESTOR_NAME));
            assertTrue(output.contains(STOCK_NAME));
            assertTrue(output.contains(STOCK_PRICE.toString()));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void shouldHandleEmptyObserverList() {
        assertDoesNotThrow(() -> observerStock.notifyObservers(stock));
    }

    @Test
    void shouldNotNotifyRemovedObserver() {
        Observer observer = investor;
        observerStock.register(observer);
        observerStock.remove(observer);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            observerStock.notifyObservers(stock);
            String output = outputStream.toString();

            assertTrue(output.isEmpty());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void shouldRegisterMultipleDifferentObservers() {
        Observer observer1 = investor;
        Observer observer2 = new Investor("Maria");

        assertDoesNotThrow(() -> {
            observerStock.register(observer1);
            observerStock.register(observer2);
        });
    }
}
