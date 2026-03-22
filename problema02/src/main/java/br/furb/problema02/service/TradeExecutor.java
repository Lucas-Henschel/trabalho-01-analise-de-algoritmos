package br.furb.problema02.service;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.*;
import br.furb.problema02.model.stock.*;
import br.furb.problema02.order.IOrderType;

import java.math.BigDecimal;
import java.util.Optional;

public class TradeExecutor {
    private final StockInfo stockInfo;
    private final StockState stockState;

    public TradeExecutor(StockInfo stockInfo, StockState stockState) {
        this.stockInfo = stockInfo;
        this.stockState = stockState;
    }

    public TradeResult executeMatch(Stock stock, IOrderType newOrder, IOrderType matchedOrder) {
        removeMatchedOrders(newOrder, matchedOrder);
        updateStockValue(stock, matchedOrder.getOrderValue());
        return TradeResult.matched(newOrder, matchedOrder);
    }

    private void removeMatchedOrders(IOrderType newOrder, IOrderType matchedOrder) {
        orders().remove(matchedOrder);
        orders().remove(newOrder);
    }

    private void updateStockValue(Stock stock, BigDecimal newValue) {
        stockInfo.setValue(newValue);
        stockState.notifyObservers(stock);
    }

    private Orders orders() {
        return stockState.getOrders();
    }

    public TradeResult processOrder(Stock stock, IOrderType newOrder, OrderTypeEnum orderType) {
        Optional<IOrderType> matchedOrder = orders().findByTypeAndValue(orderType.opposite(), newOrder.getOrderValue());
        return matchedOrder.map(matched -> executeMatch(stock, newOrder, matched))
                .orElseGet(() -> addPendingOrder(newOrder));
    }

    private TradeResult addPendingOrder(IOrderType newOrder) {
        orders().add(newOrder);
        return TradeResult.pending(newOrder);
    }
}
