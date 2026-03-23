package br.furb.problema02.model.stock;

import br.furb.problema02.conditionalOrder.ConditionalOrder;
import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.trade.TradeResult;
import br.furb.problema02.observer.Observer;
import br.furb.problema02.observer.Subject;

import java.math.BigDecimal;

public class Stock implements Subject {
    private final StockInfo stockInfo;
    private final StockRuntime stockRuntime;

    public Stock(String name, BigDecimal value) {
        stockInfo = new StockInfo(name, value);
        stockRuntime = new StockRuntime(stockInfo);
    }
    
    public String getName() {
        return stockInfo.getName();
    }

    public BigDecimal getValue() {
        return stockInfo.getValue();
    }

    public boolean hasPendingOrders() {
        return stockRuntime.hasPendingOrders();
    }

    public int pendingOrdersCount() {
        return stockRuntime.pendingOrdersCount();
    }

    public TradeResult placeOrder(String investorName, BigDecimal orderValue, OrderTypeEnum orderType) {
        return stockRuntime.placeOrder(this, investorName, orderValue, orderType);
    }

    @Override
    public void registerObserver(Observer observer) {
        stockRuntime.registerObserver(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        stockRuntime.removeObserver(observer);
    }

    @Override
    public void notifyObservers() {
        stockRuntime.notifyObservers(this);
    }

    public void scheduleConditionalOrder(ConditionalOrder conditionalOrder) {
        stockRuntime.scheduleConditionalOrder(conditionalOrder);
    }
}
