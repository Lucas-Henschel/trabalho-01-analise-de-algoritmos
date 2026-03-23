package br.furb.problema02.service.trade;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.trade.TradeResult;
import br.furb.problema02.order.IOrderType;
import br.furb.problema02.service.StockState;

import java.math.BigDecimal;
import java.util.Optional;

public class TradeOrderBook {
    private final StockState stockState;

    public TradeOrderBook(StockState stockState) {
        this.stockState = stockState;
    }

    public Optional<IOrderType> findMatch(OrderTypeEnum orderType, BigDecimal orderValue) {
        return stockState.getOrders().findByTypeAndValue(orderType, orderValue);
    }

    public void removeMatchedOrders(IOrderType newOrder, IOrderType matchedOrder) {
        stockState.getOrders().remove(matchedOrder);
        stockState.getOrders().remove(newOrder);
    }

    public TradeResult addPendingOrder(IOrderType newOrder) {
        stockState.getOrders().add(newOrder);
        
        return TradeResult.pending(newOrder);
    }
}
