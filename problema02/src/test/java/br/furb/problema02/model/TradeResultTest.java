package br.furb.problema02.model;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.trade.TradeResult;
import br.furb.problema02.order.OrderTypeBuy;
import br.furb.problema02.order.OrderTypeSell;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TradeResultTest {

    @Test
    void shouldCreatePendingTradeResult() {
        OrderTypeBuy incomingOrder = new OrderTypeBuy("Marina", new BigDecimal("25.00"));

        TradeResult result = TradeResult.pending(incomingOrder);

        assertEquals(incomingOrder, result.getIncomingOrder());
        assertTrue(result.isPending());
        assertFalse(result.hasMatch());
        assertTrue(result.getMatchedOrder().isEmpty());
        assertTrue(result.getNegotiatedValue().isEmpty());
        assertEquals(OrderTypeEnum.BUY, result.getIncomingOrder().getOrderType());
    }

    @Test
    void shouldCreateMatchedTradeResult() {
        OrderTypeBuy incomingOrder = new OrderTypeBuy("Marina", new BigDecimal("25.00"));
        OrderTypeSell matchedOrder = new OrderTypeSell("Lucas", new BigDecimal("25.00"));

        TradeResult result = TradeResult.matched(incomingOrder, matchedOrder);

        assertEquals(incomingOrder, result.getIncomingOrder());
        assertFalse(result.isPending());
        assertTrue(result.hasMatch());
        assertEquals(matchedOrder, result.getMatchedOrder().orElseThrow());
        assertEquals(matchedOrder.getOrderValue(), result.getNegotiatedValue().orElseThrow());
        assertEquals(OrderTypeEnum.SELL, result.getMatchedOrder().orElseThrow().getOrderType());
    }
}
