package br.furb.problema02.model.stock;

import br.furb.problema02.conditionalOrder.ConditionalOrder;
import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.factories.OrderTypeFactory;
import br.furb.problema02.model.TradeResult;
import br.furb.problema02.service.StockState;
import br.furb.problema02.service.TradeExecutor;

import java.math.BigDecimal;

class StockOrderBook {
    private final StockState stockState;
    private final TradeExecutor tradeExecutor;

    StockOrderBook(StockInfo stockInfo) {
        stockState = new StockState();
        tradeExecutor = new TradeExecutor(stockInfo, stockState);
    }

    public boolean hasPendingOrders() { 
        return !stockState.getOrders().isEmpty(); 
    }

    public int pendingOrdersCount() { 
        return stockState.getOrders().size(); 
    }

    public TradeResult placeOrder(Stock stock, String investorName, BigDecimal orderValue, OrderTypeEnum orderType) { 
        return tradeExecutor.processOrder(stock, OrderTypeFactory.createOrder(investorName, orderValue, orderType), orderType); 
    }

    public void scheduleConditionalOrder(ConditionalOrder conditionalOrder) { 
        stockState.addConditionalOrder(conditionalOrder); 
    }
}
