package br.furb.problema02.service.trade;

import br.furb.problema02.conditionalOrder.ConditionalOrder;
import br.furb.problema02.model.stock.Stock;
import br.furb.problema02.order.IOrderType;
import br.furb.problema02.service.StockState;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ConditionalOrderProcessor {
    private final StockState stockState;
    private boolean processingConditionalOrders;

    public ConditionalOrderProcessor(StockState stockState) {
        this.stockState = stockState;
    }

    public void process(Stock stock, TradeOrderProcessor tradeOrderProcessor) {
        if (processingConditionalOrders) return;
        processingConditionalOrders = true;

        try {
            List<ConditionalOrder> ordersToExecute = collectConditionalOrders(stock.getValue());

            while (hasOrdersToExecute(ordersToExecute)) {
                executeConditionalOrders(stock, ordersToExecute, tradeOrderProcessor);
                ordersToExecute = collectConditionalOrders(stock.getValue());
            }
        } finally {
            processingConditionalOrders = false;
        }
    }

    private boolean hasOrdersToExecute(List<ConditionalOrder> ordersToExecute) {
        return ordersToExecute.isEmpty() == false;
    }

    private List<ConditionalOrder> collectConditionalOrders(BigDecimal stockValue) {
        List<ConditionalOrder> ordersToExecute = new ArrayList<>();

        for (ConditionalOrder conditionalOrder : stockState.getConditionalOrders()) {
            if (conditionalOrder.shouldExecute(stockValue)) {
                ordersToExecute.add(conditionalOrder);
            }
        }

        return ordersToExecute;
    }

    private void executeConditionalOrders(Stock stock, List<ConditionalOrder> ordersToExecute, TradeOrderProcessor tradeOrderProcessor) {
        for (ConditionalOrder conditionalOrder : ordersToExecute) {
            boolean wasRemoved = stockState.getConditionalOrders().remove(conditionalOrder);
            if (wasRemoved == false) continue;
            
            IOrderType order = conditionalOrder.getOrder();
            tradeOrderProcessor.process(stock, order, order.getOrderType());
        }
    }
}
