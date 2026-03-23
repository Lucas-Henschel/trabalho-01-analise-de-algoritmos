package br.furb.problema02.model.stock;

import br.furb.problema02.conditionalOrder.ConditionalOrder;
import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.factories.OrderTypeFactory;
import br.furb.problema02.model.Orders;
import br.furb.problema02.model.TradeResult;
import br.furb.problema02.observer.Observer;
import br.furb.problema02.observer.ObserverStock;
import br.furb.problema02.observer.Subject;
import br.furb.problema02.order.IOrderType;
import br.furb.problema02.service.StockState;
import br.furb.problema02.service.TradeExecutor;

import java.math.BigDecimal;

public class Stock implements Subject {
    private final StockInfo stockInfo;
    private final StockState stockState = new StockState();
    private final ObserverStock observerStocks = new ObserverStock();
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
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome da ação inválido");
        }

        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor da ação inválido");
        }

        this.stockInfo = new StockInfo(name, value);
        this.tradeExecutor = new TradeExecutor(stockInfo, stockState);
    }

    public TradeResult placeOrder(String investorName, BigDecimal orderValue, OrderTypeEnum orderType) {
        if (investorName == null || investorName.isBlank()) {
            throw new IllegalArgumentException("Nome do investidor inválido");
        }

        if (orderValue == null || orderValue.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor da ordem inválido");
        }

        IOrderType newOrder = OrderTypeFactory.createOrder(investorName, orderValue, orderType);
        return tradeExecutor.processOrder(this, newOrder, orderType);
    }

    private Orders orders() {
        return stockState.getOrders();
    }

    @Override
    public void registerObserver(Observer observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observador inválido");
        }

        observerStocks.register(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observador inválido");
        }

        observerStocks.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observerStocks.notifyObservers(this);
    }

    public void scheduleConditionalOrder(ConditionalOrder conditionalOrder) {
        stockState.addConditionalOrder(conditionalOrder);
    }
}
