package br.furb.problema02.service;

import br.furb.problema02.model.Orders;
import br.furb.problema02.model.stock.Stock;
import br.furb.problema02.observer.Observer;
import br.furb.problema02.observer.ObserverStock;

public class StockState {
    private final Orders orders = new Orders();
    private final ObserverStock observerStocks = new ObserverStock();

    public StockState() {
    }

    public Orders getOrders() {
        return orders;
    }

    public void registerObserverStocks(Observer observer) {
        observerStocks.register(observer);
    }

    public void notifyObservers(Stock stock) {
        observerStocks.notifyObservers(stock);
    }
}
