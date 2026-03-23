package br.furb.problema02.service;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.stock.Stock;
import br.furb.problema02.model.stock.StockInfo;
import br.furb.problema02.model.trade.TradeResult;
import br.furb.problema02.order.IOrderType;
import br.furb.problema02.service.trade.ConditionalOrderProcessor;
import br.furb.problema02.service.trade.TradeMatchWorkflow;
import br.furb.problema02.service.trade.TradeOrderBook;

import java.util.Optional;

public class TradeExecutor {
    private final TradeOrderBook tradeOrderBook;
    private final TradeMatchWorkflow tradeMatchWorkflow;

    public TradeExecutor(StockInfo stockInfo, StockState stockState) {
        tradeOrderBook = new TradeOrderBook(stockState);
        tradeMatchWorkflow = new TradeMatchWorkflow(stockInfo, new ConditionalOrderProcessor(stockState));
    }

    public TradeResult executeMatch(Stock stock, IOrderType newOrder, IOrderType matchedOrder) {
        return tradeMatchWorkflow.execute(stock, newOrder, matchedOrder, tradeOrderBook, this::processOrder);
    }

    public TradeResult processOrder(Stock stock, IOrderType newOrder, OrderTypeEnum orderType) {
        Optional<IOrderType> matchedOrder = tradeOrderBook.findMatch(orderType.opposite(), newOrder.getOrderValue());
        
        return matchedOrder
            .map(matched -> executeMatch(stock, newOrder, matched))
            .orElseGet(() -> tradeOrderBook.addPendingOrder(newOrder));
    }
}
