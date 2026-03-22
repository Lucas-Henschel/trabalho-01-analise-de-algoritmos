package br.furb.problema02.model.stock;

import br.furb.problema02.model.Orders;
import br.furb.problema02.observer.Observer;
import br.furb.problema02.observer.Subject;

import java.math.BigDecimal;

public class Stock implements Subject {
    private StockInfo stockInfo;
    private StockState stockState = new StockState();

    public String getName() {
        return stockInfo.getName();
    }

    public BigDecimal getValue() {
        return stockInfo.getValue();
    }

    public Orders getOrders() {
        return stockState.getOrders();
    }

    public Stock(String name, BigDecimal value) {
        this.stockInfo = new StockInfo(name, value);
    }

    @Override
    public void registerObserver(Observer observer) {
        stockState.getObserverStocks().register(observer);
    }
}
