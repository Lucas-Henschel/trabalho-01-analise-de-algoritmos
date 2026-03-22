package br.furb.problema02.model;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.stock.Stock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

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
    void shouldRegisterForStockUpdates() {
        assertDoesNotThrow(() -> investor.registerForStockUpdates(stock));
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

        /* 
        o teste ta passando mesmo com o investorName = "Comprador" e "Vendedor",
        acredito que esse comportamento ta zoado, precisamos revisar a implementação
         */
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
