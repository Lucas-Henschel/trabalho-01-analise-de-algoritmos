package br.furb.problema02.model.stock;

import br.furb.problema02.conditionalorder.ConditionalOrder;
import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.factories.OrderTypeFactory;
import br.furb.problema02.model.*;
import br.furb.problema02.observer.*;
import br.furb.problema02.order.IOrderType;
import br.furb.problema02.service.*;

import java.math.BigDecimal;

public class Stock implements Subject {
    private final StockInfo stockInfo;
    private final StockState stockState = new StockState();
    private final TradeExecutor tradeExecutor;

    public String getName() {
        return stockInfo.getName();
    }

    public BigDecimal getValue() {
        return stockInfo.getValue();
    }

    public boolean hasPendingOrders() {
        return !orders().isEmpty();
    }

    public int pendingOrdersCount() {
        return orders().size();
    }

    public Stock(String name, BigDecimal value) {
        this.stockInfo = new StockInfo(name, value);
        this.tradeExecutor = new TradeExecutor(stockInfo, stockState);
    }

    public TradeResult placeOrder(String investorName, BigDecimal orderValue, OrderTypeEnum orderType) {
        IOrderType newOrder = OrderTypeFactory.createOrder(investorName, orderValue, orderType);
        return tradeExecutor.processOrder(this, newOrder, orderType);
    }

    private Orders orders() {
        return stockState.getOrders();
    }

    @Override
    public void registerObserver(Observer observer) {
        stockState.registerObserverStocks(observer);
    }
    
    public void scheduleConditionalOrder(ConditionalOrder conditionalOrder) {
        stockState.addConditionalOrder(conditionalOrder);
    }
}
