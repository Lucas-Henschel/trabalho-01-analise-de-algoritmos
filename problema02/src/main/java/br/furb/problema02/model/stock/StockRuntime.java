package br.furb.problema02.model.stock;

import br.furb.problema02.conditionalOrder.ConditionalOrder;
import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.TradeResult;
import br.furb.problema02.observer.Observer;
import br.furb.problema02.observer.ObserverStock;

import java.math.BigDecimal;

class StockRuntime {
    private final StockOrderBook stockOrderBook;
    private final ObserverStock observerStock;

    StockRuntime(StockInfo stockInfo) {
        stockOrderBook = new StockOrderBook(stockInfo);
        observerStock = new ObserverStock();
    }

    public boolean hasPendingOrders() { 
        return stockOrderBook.hasPendingOrders(); 
    }

    public int pendingOrdersCount() { return stockOrderBook.pendingOrdersCount(); }

    public TradeResult placeOrder(Stock stock, String investorName, BigDecimal orderValue, OrderTypeEnum orderType) { 
        return stockOrderBook.placeOrder(stock, investorName, orderValue, orderType); 
    }

    public void registerObserver(Observer observer) { 
        observerStock.register(observer); 
    }

    public void removeObserver(Observer observer) { 
        observerStock.remove(observer); 
    }

    public void notifyObservers(Stock stock) { 
        observerStock.notifyObservers(stock); 
    }

    public void scheduleConditionalOrder(ConditionalOrder conditionalOrder) { 
        stockOrderBook.scheduleConditionalOrder(conditionalOrder); 
    }
}
