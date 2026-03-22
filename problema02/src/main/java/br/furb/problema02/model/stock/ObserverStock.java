package br.furb.problema02.model.stock;

import br.furb.problema02.observer.Observer;

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
}
