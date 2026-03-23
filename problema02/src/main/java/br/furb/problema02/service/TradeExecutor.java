package br.furb.problema02.service;

import br.furb.problema02.conditionalOrder.ConditionalOrder;
import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.order.Orders;
import br.furb.problema02.model.stock.Stock;
import br.furb.problema02.model.stock.StockInfo;
import br.furb.problema02.model.trade.TradeResult;
import br.furb.problema02.order.IOrderType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TradeExecutor {
    private final StockInfo stockInfo;
    private final StockState stockState;
    private boolean processingConditionalOrders = false;

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
        stock.notifyObservers();
        processConditionalOrders(stock);
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

    private void processConditionalOrders(Stock stock) {
        if (processingConditionalOrders) {
            return;
        }

        processingConditionalOrders = true;

        try {
            List<ConditionalOrder> toExecute = collectConditionalOrdersToExecute();

            while (!toExecute.isEmpty()) {
                executeConditionalOrders(stock, toExecute);
                toExecute = collectConditionalOrdersToExecute();
            }
        } finally {
            processingConditionalOrders = false;
        }
    }

    private List<ConditionalOrder> collectConditionalOrdersToExecute() {
        List<ConditionalOrder> toExecute = new ArrayList<>();
        for (ConditionalOrder conditional : stockState.getConditionalOrders()) {
            if (conditional.shouldExecute(stockInfo.getValue())) {
                toExecute.add(conditional);
            }
        }
        return toExecute;
    }

    private void executeConditionalOrders(Stock stock, List<ConditionalOrder> toExecute) {
        for (ConditionalOrder conditional : toExecute) {
            if (!stockState.getConditionalOrders().remove(conditional)) {
                continue;
            }

            IOrderType order = conditional.getOrder();
            processOrder(stock, order, order.getOrderType());
        }
    }
}
