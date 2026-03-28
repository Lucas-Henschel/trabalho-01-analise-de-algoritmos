package br.furb.problema03.facades;

import java.util.List;

import br.furb.problema03.enums.IntelligentAirConditionerEnum;
import br.furb.problema03.factories.AirConditionerFactory;
import br.furb.problema03.strategies.airConditioner.AirConditionerStrategy;

public class IntelligentAirConditionerFacade {
    private final List<AirConditionerStrategy> airConditioners;

    public IntelligentAirConditionerFacade(List<IntelligentAirConditionerEnum> airConditionerTypes) {
        this.airConditioners = airConditionerTypes.stream()
                .map(AirConditionerFactory::createAirConditionerStrategy)
                .toList();
    }

    public void turnOnAll() {
        airConditioners.forEach(AirConditionerStrategy::turnOn);
    }

    public void turnOffAll() {
        airConditioners.forEach(AirConditionerStrategy::turnOff);
    }

    public void increaseTemperatureAll() {
        airConditioners.forEach(AirConditionerStrategy::increaseTemperature);
    }

    public void decreaseTemperatureAll() {
        airConditioners.forEach(AirConditionerStrategy::decreaseTemperature);
    }

    public void defineTemperatureAll(int temperature) {
        airConditioners.forEach(ac -> ac.defineTemperature(temperature));
    }
}
