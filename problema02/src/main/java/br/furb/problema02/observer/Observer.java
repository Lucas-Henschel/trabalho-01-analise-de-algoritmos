package br.furb.problema02.observer;

import br.furb.problema02.model.stock.Stock;

public interface Observer {
    void changedValue(Stock stock);
}
