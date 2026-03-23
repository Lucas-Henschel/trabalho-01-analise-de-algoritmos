package br.furb.problema02.conditionalorder;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.factories.ConditionalOrderFactory;
import br.furb.problema02.model.Investor;
import br.furb.problema02.model.TradeResult;
import br.furb.problema02.model.stock.Stock;

class ConditionalOrderTest {

	@Test
    void shouldReturnTrueWhenPriceIsBelowTarget() {
        ICondition condition = new PriceBelowCondition(new BigDecimal("50"));

        assertTrue(condition.verifyCondition(new BigDecimal("49")));
        assertTrue(condition.verifyCondition(new BigDecimal("50")));
        assertFalse(condition.verifyCondition(new BigDecimal("51")));
    }
	
	@Test
    void shouldReturnTrueWhenPriceIsAboveTarget() {
        ICondition condition = new PriceAboveCondition(new BigDecimal("50"));

        assertTrue(condition.verifyCondition(new BigDecimal("51")));
        assertTrue(condition.verifyCondition(new BigDecimal("50")));
        assertFalse(condition.verifyCondition(new BigDecimal("49")));
    }
	
    @Test
    void shouldCreateConditionalOrderWithFactory() {
        ConditionalOrder order = ConditionalOrderFactory.create(
                "João",
                new BigDecimal("30"),
                OrderTypeEnum.BUY,
                new PriceBelowCondition(new BigDecimal("35"))
        );

        assertNotNull(order);
        assertEquals("João", order.getOrder().getInvestorName());
        assertEquals(new BigDecimal("30"), order.getOrder().getOrderValue());
        assertEquals(OrderTypeEnum.BUY, order.getOrder().getOrderType());
    }
    
   @Test
   void shouldExecuteConditionalOrderWhenConditionIsMet() {
       Stock stock = new Stock("PETR4", new BigDecimal("30"));

       Investor joao = new Investor("João");

       // João quer comprar a 25 quando cair pra 28
       ConditionalOrder conditional = ConditionalOrderFactory.create(
               joao.getName(),
               new BigDecimal("25"),
               OrderTypeEnum.BUY,
               new PriceBelowCondition(new BigDecimal("28"))
       );

       joao.scheduleConditionalOrder(stock, conditional);

       // cria uma ordem de venda compatível
       stock.placeOrder("Maria", new BigDecimal("25"), OrderTypeEnum.SELL);

       // ainda não executou (preço não caiu)
       assertEquals(1, stock.pendingOrdersCount());

       // agora força queda de preço
       stock.placeOrder("Carlos", new BigDecimal("28"), OrderTypeEnum.SELL);
       stock.placeOrder("Ana", new BigDecimal("28"), OrderTypeEnum.BUY);

       // agora a ordem condicional deve ter sido executada
       assertEquals(new BigDecimal("25"), stock.getValue());
   }

    @Test
    void shouldNotLeavePendingOrderAfterConditionalOrderMatchesImmediately() {
        Stock stock = new Stock("PETR4", new BigDecimal("30"));

        Investor joao = new Investor("João");

        ConditionalOrder conditional = ConditionalOrderFactory.create(
            joao.getName(),
            new BigDecimal("25"),
            OrderTypeEnum.BUY,
            new PriceBelowCondition(new BigDecimal("28"))
        );

        joao.scheduleConditionalOrder(stock, conditional);

        stock.placeOrder("Maria", new BigDecimal("25"), OrderTypeEnum.SELL);
        stock.placeOrder("Carlos", new BigDecimal("28"), OrderTypeEnum.SELL);
        stock.placeOrder("Ana", new BigDecimal("28"), OrderTypeEnum.BUY);

        assertEquals(new BigDecimal("25"), stock.getValue());
        assertEquals(0, stock.pendingOrdersCount());
    }

    @Test
    void shouldNotCreateGhostOrderAfterConditionalOrderExecutes() {
        Stock stock = new Stock("PETR4", new BigDecimal("30"));

        Investor joao = new Investor("João");

        ConditionalOrder conditional = ConditionalOrderFactory.create(
            joao.getName(),
            new BigDecimal("25"),
            OrderTypeEnum.BUY,
            new PriceBelowCondition(new BigDecimal("28"))
        );

        joao.scheduleConditionalOrder(stock, conditional);

        stock.placeOrder("Maria", new BigDecimal("25"), OrderTypeEnum.SELL);
        stock.placeOrder("Carlos", new BigDecimal("28"), OrderTypeEnum.SELL);
        stock.placeOrder("Ana", new BigDecimal("28"), OrderTypeEnum.BUY);

        TradeResult result = stock.placeOrder("Pedro", new BigDecimal("25"), OrderTypeEnum.SELL);

        assertTrue(result.isPending());
        assertFalse(result.hasMatch());
        assertEquals(1, stock.pendingOrdersCount());
        assertEquals(new BigDecimal("25"), stock.getValue());
    }
    
    @Test
    void shouldNotExecuteConditionalOrderWhenConditionIsNotMet() {
        Stock stock = new Stock("VALE3", new BigDecimal("60"));

        Investor joao = new Investor("João");

        ConditionalOrder conditional = ConditionalOrderFactory.create(
                joao.getName(),
                new BigDecimal("50"),
                OrderTypeEnum.BUY,
                new PriceBelowCondition(new BigDecimal("40")) // nunca vai acontecer
        );

        joao.scheduleConditionalOrder(stock, conditional);

        // movimenta preço, mas não o suficiente
        stock.placeOrder("Maria", new BigDecimal("58"), OrderTypeEnum.SELL);
        stock.placeOrder("Carlos", new BigDecimal("58"), OrderTypeEnum.BUY);

        // ordem condicional continua não executada
        assertEquals(new BigDecimal("58"), stock.getValue());
        assertEquals(0, stock.pendingOrdersCount());
    }
    
   @Test
   void shouldRemoveConditionalOrderAfterExecution() {
       Stock stock = new Stock("PETR4", new BigDecimal("30"));

       Investor joao = new Investor("João");

       ConditionalOrder conditional = ConditionalOrderFactory.create(
               joao.getName(),
               new BigDecimal("25"),
               OrderTypeEnum.BUY,
               new PriceBelowCondition(new BigDecimal("29"))
       );

       joao.scheduleConditionalOrder(stock, conditional);

       // cria match possível
       stock.placeOrder("Maria", new BigDecimal("25"), OrderTypeEnum.SELL);

       // força execução da condicional
       stock.placeOrder("Carlos", new BigDecimal("28"), OrderTypeEnum.SELL);
       stock.placeOrder("Ana", new BigDecimal("28"), OrderTypeEnum.BUY);

       // valor após execução da condicional
       assertEquals(new BigDecimal("25"), stock.getValue());

       // agora executa outro match normal
       stock.placeOrder("Pedro", new BigDecimal("28"), OrderTypeEnum.SELL);
       stock.placeOrder("Lucas", new BigDecimal("28"), OrderTypeEnum.BUY);

       // valor muda normalmente (isso é esperado!)
       assertEquals(new BigDecimal("28"), stock.getValue());
   }

    @Test
    void shouldNotifyObserversInChronologicalOrderForTriggerAndConditionalMatch() {
        Stock stock = new Stock("PETR4", new BigDecimal("30"));
        Investor observer = new Investor("Observador");
        Investor joao = new Investor("João");

        observer.registerForStockUpdates(stock);

        ConditionalOrder conditional = ConditionalOrderFactory.create(
            joao.getName(),
            new BigDecimal("25"),
            OrderTypeEnum.BUY,
            new PriceBelowCondition(new BigDecimal("28"))
        );

        joao.scheduleConditionalOrder(stock, conditional);

        stock.placeOrder("Maria", new BigDecimal("25"), OrderTypeEnum.SELL);
        stock.placeOrder("Carlos", new BigDecimal("28"), OrderTypeEnum.SELL);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            stock.placeOrder("Ana", new BigDecimal("28"), OrderTypeEnum.BUY);
        } finally {
            System.setOut(originalOut);
        }

        String[] lines = outputStream.toString().trim().split("\\R");

        assertEquals(2, lines.length);
        assertTrue(lines[0].contains("mudou para 28"));
        assertTrue(lines[1].contains("mudou para 25"));
    }
}
