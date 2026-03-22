package br.furb.problema02.observer;

import br.furb.problema02.model.stock.Stock;

import java.util.ArrayList;
import java.util.List;

public class ObserverStock {
    private final List<Observer> observerStocks = new ArrayList<>();

    public void register(Observer observer) {
        if (observerStocks.contains(observer)) {
            throw new IllegalArgumentException("Investidor já está observando a ação");
        }

        observerStocks.add(observer);
    }

    public void notifyObservers(Stock stock) {
        observerStocks.forEach(observer -> observer.changedValue(stock));
    }
}
