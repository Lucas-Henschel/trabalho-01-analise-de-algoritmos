package br.furb.problema03.facades.blinds;

import br.furb.analise.algoritmos.PersianaSolarius;

public class PersianaSolariusStrategy implements BlindsStrategy {
    private final PersianaSolarius blinds;

    public PersianaSolariusStrategy(PersianaSolarius blinds) {
        this.blinds = blinds;
    }

    @Override
    public void open() {
        blinds.subirPersiana();
    }

    @Override
    public void close() {
        blinds.descerPersiana();
    }
}
