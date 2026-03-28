package br.furb.problema03.facades;

import br.furb.problema03.strategies.blinds.BlindStrategy;

import java.util.List;

public class IntelligentBlindFacade {
    private final List<BlindStrategy> blinds;

    public IntelligentBlindFacade(List<BlindStrategy> blinds) {
        this.blinds = List.copyOf(blinds);
    }

    public void openAll() {
        blinds.forEach(BlindStrategy::open);
    }

    public void closeAll() {
        blinds.forEach(BlindStrategy::close);
    }
}
