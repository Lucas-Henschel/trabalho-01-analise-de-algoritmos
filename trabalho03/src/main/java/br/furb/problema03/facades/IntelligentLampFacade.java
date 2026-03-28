package br.furb.problema03.facades;

import br.furb.problema03.strategies.lamps.LampStrategy;

import java.util.List;

public class IntelligentLampFacade {
    private final List<LampStrategy> lamps;

    public IntelligentLampFacade(List<LampStrategy> lamps) {
        this.lamps = List.copyOf(lamps);
    }

    public void turnOnAll() {
        lamps.forEach(LampStrategy::turnOn);
    }

    public void turnOffAll() {
        lamps.forEach(LampStrategy::turnOff);
    }
}
