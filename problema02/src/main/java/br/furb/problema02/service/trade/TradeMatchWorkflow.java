package br.furb.problema02.service.trade;

import br.furb.problema02.model.stock.Stock;
import br.furb.problema02.model.stock.StockInfo;
import br.furb.problema02.model.trade.TradeResult;
import br.furb.problema02.order.IOrderType;

public class TradeMatchWorkflow {
    private final StockInfo stockInfo;
    private final ConditionalOrderProcessor conditionalOrderProcessor;

    public TradeMatchWorkflow(StockInfo stockInfo, ConditionalOrderProcessor conditionalOrderProcessor) {
        this.stockInfo = stockInfo;
        this.conditionalOrderProcessor = conditionalOrderProcessor;
    }

    public TradeResult execute(
        Stock stock,
        IOrderType newOrder,
        IOrderType matchedOrder,
        TradeOrderBook tradeOrderBook,
        TradeOrderProcessor tradeOrderProcessor
    ) {
        tradeOrderBook.removeMatchedOrders(newOrder, matchedOrder);
        stockInfo.setValue(matchedOrder.getOrderValue());
        
        stock.notifyObservers();
        conditionalOrderProcessor.process(stock, tradeOrderProcessor);

        return TradeResult.matched(newOrder, matchedOrder);
    }
}
