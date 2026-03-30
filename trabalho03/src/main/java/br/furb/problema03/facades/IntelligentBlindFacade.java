package br.furb.problema03.facades;

import br.furb.problema03.enums.IntelligentBlindEnum;
import br.furb.problema03.factories.BlindFactory;
import br.furb.problema03.strategies.blinds.BlindStrategy;

import java.util.List;

public class IntelligentBlindFacade {
    private final List<BlindStrategy> blinds;

    public IntelligentBlindFacade(List<IntelligentBlindEnum> blindTypes) {
        this.blinds = blindTypes.stream()
            .map(BlindFactory::createBlindFactory)
            .toList();
    }

    public void openAll() {
        blinds.forEach(BlindStrategy::open);
    }

    public void closeAll() {
        blinds.forEach(BlindStrategy::close);
    }
}
