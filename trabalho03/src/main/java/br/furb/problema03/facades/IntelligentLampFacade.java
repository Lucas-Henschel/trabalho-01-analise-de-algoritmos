package br.furb.problema03.facades;

import br.furb.problema03.enums.IntelligentLampEnum;
import br.furb.problema03.factories.LampFactory;
import br.furb.problema03.strategies.lamps.LampStrategy;

import java.util.List;

public class IntelligentLampFacade {
    private final List<LampStrategy> lamps;

    public IntelligentLampFacade(List<IntelligentLampEnum> lampTypes) {
        this.lamps = lampTypes.stream()
            .map(LampFactory::createIntelligentLampFactory)
            .toList();
    }

    public void turnOnAll() {
        lamps.forEach(LampStrategy::turnOn);
    }

    public void turnOffAll() {
        lamps.forEach(LampStrategy::turnOff);
    }
}
