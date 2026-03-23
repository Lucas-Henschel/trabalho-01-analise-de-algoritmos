package br.furb.problema02.model;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.factories.ConditionalOrderFactory;
import br.furb.problema02.model.investor.Investor;
import br.furb.problema02.model.stock.Stock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InvestorTest {

    private static final String STOCK_NAME = "PETR4";
    private static final BigDecimal STOCK_PRICE = new BigDecimal("30.00");
    private static final BigDecimal PRICE_50_00 = new BigDecimal("50.00");
    private static final String INVESTOR_NAME = "João Silva";

    private Stock stock;
    private Investor investor;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        stock = new Stock(STOCK_NAME, STOCK_PRICE);
        investor = new Investor(INVESTOR_NAME);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void shouldCreateInvestorWithName() {
        assertEquals(INVESTOR_NAME, investor.getName());
    }

    @Test
    void shouldThrowExceptionWhenCreatingInvestorWithNullName() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Investor(null)
        );

        assertEquals("Nome do investidor inválido", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingInvestorWithBlankName() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Investor("   ")
        );

        assertEquals("Nome do investidor inválido", exception.getMessage());
    }

    @Test
    void shouldPlaceBuyOrder() {
        TradeResult result = investor.orderRegister(stock, STOCK_PRICE, OrderTypeEnum.BUY);

        assertNotNull(result);
        assertEquals(INVESTOR_NAME, result.getIncomingOrder().getInvestorName());
        assertEquals(STOCK_PRICE, result.getIncomingOrder().getOrderValue());
        assertEquals(OrderTypeEnum.BUY, result.getIncomingOrder().getOrderType());
    }

    @Test
    void shouldPlaceSellOrder() {
        TradeResult result = investor.orderRegister(stock, STOCK_PRICE, OrderTypeEnum.SELL);

        assertNotNull(result);
        assertEquals(INVESTOR_NAME, result.getIncomingOrder().getInvestorName());
        assertEquals(STOCK_PRICE, result.getIncomingOrder().getOrderValue());
        assertEquals(OrderTypeEnum.SELL, result.getIncomingOrder().getOrderType());
    }

    @Test
    void shouldThrowExceptionWhenRegisteringOrderWithNullStock() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> investor.orderRegister(null, STOCK_PRICE, OrderTypeEnum.BUY)
        );

        assertEquals("Ação inválida", exception.getMessage());
    }

    @Test
    void shouldRegisterForStockUpdates() {
        assertDoesNotThrow(() -> investor.registerForStockUpdates(stock));
    }

    @Test
    void shouldThrowExceptionWhenRegisteringForUpdatesWithNullStock() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> investor.registerForStockUpdates(null)
        );

        assertEquals("Ação inválida", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSchedulingConditionalOrderWithNullStock() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> investor.scheduleConditionalOrder(
                null,
                ConditionalOrderFactory.create(
                    INVESTOR_NAME,
                    STOCK_PRICE,
                    OrderTypeEnum.BUY,
                    stockValue -> true
                )
            )
        );

        assertEquals("Ação inválida", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSchedulingConditionalOrderWithNullOrder() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> investor.scheduleConditionalOrder(stock, null)
        );

        assertEquals("Ordem inválida", exception.getMessage());
    }

    @Test
    void shouldReceiveNotificationWhenStockValueChanges() {
        investor.registerForStockUpdates(stock);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            investor.changedValue(stock);
            String output = outputStream.toString().trim();

            assertTrue(output.contains("Investidor " + INVESTOR_NAME + " notificado"));
            assertTrue(output.contains(STOCK_NAME));
            assertTrue(output.contains(STOCK_PRICE.toString()));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void shouldNotifyMultipleInvestorsWhenValueChanges() {
        Investor investor2 = new Investor("Paula");

        investor.registerForStockUpdates(stock);
        investor2.registerForStockUpdates(stock);

        stock.placeOrder("Comprador", PRICE_50_00, OrderTypeEnum.BUY);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            stock.placeOrder("Vendedor", PRICE_50_00, OrderTypeEnum.SELL);
            String output = outputStream.toString();

            assertTrue(output.contains("João Silva"));
            assertTrue(output.contains("Paula"));
        } finally {
            System.setOut(originalOut);
        }
    }
}
