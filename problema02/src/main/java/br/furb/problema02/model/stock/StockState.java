package br.furb.problema02.model.stock;

import br.furb.problema02.model.Orders;

public class StockState {
    private final Orders orders = new Orders();
    private final ObserverStock observerStocks = new ObserverStock();

    public StockState() {
    }

    public Orders getOrders() {
        return orders;
    }

    public ObserverStock getObserverStocks() {
        return observerStocks;
    }
}
