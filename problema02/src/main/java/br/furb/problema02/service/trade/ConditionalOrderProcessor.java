package br.furb.problema02.service.trade;

import br.furb.problema02.conditionalOrder.ConditionalOrder;
import br.furb.problema02.model.stock.Stock;
import br.furb.problema02.order.IOrderType;
import br.furb.problema02.service.StockState;

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
            List<ConditionalOrder> executableOrders = stockState.findExecutableConditionalOrders(stock.getValue());

            while (executableOrders.isEmpty() == false) {
                execute(stock, executableOrders, tradeOrderProcessor);
                executableOrders = stockState.findExecutableConditionalOrders(stock.getValue());
            }
        } finally {
            processingConditionalOrders = false;
        }
    }

    private void execute(Stock stock, List<ConditionalOrder> executableOrders, TradeOrderProcessor tradeOrderProcessor) {
        for (ConditionalOrder conditionalOrder : executableOrders) {
            if (stockState.removeConditionalOrder(conditionalOrder) == false) {
                continue;
            }

            IOrderType order = conditionalOrder.getOrder();
            tradeOrderProcessor.process(stock, order, order.getOrderType());
        }
    }
}
