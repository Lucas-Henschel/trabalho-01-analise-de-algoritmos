package br.furb.problema03.strategies.blinds;

import br.furb.analise.algoritmos.PersianaSolarius;

public class PersianaSolariusStrategy implements BlindStrategy {
    private final PersianaSolarius blind;

    public PersianaSolariusStrategy(PersianaSolarius blind) {
        this.blind = blind;
    }

    @Override
    public void open() {
        blind.subirPersiana();
    }

    @Override
    public void close() {
        blind.descerPersiana();
    }
}
