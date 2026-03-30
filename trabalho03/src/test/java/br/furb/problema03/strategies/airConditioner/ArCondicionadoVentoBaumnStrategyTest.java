package br.furb.problema03.strategies.airConditioner;

import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArCondicionadoVentoBaumnStrategyTest {
    @Test
    void shouldTurnOnVentoBaumnAirConditioner() {
        ArCondicionadoVentoBaumn airConditioner = new ArCondicionadoVentoBaumn();
        AirConditionerStrategy strategy = new ArCondicionadoVentoBaumnStrategy(airConditioner);

        strategy.turnOn();
    }

    @Test
    void shouldTurnOffVentoBaumnAirConditioner() {
        ArCondicionadoVentoBaumn airConditioner = new ArCondicionadoVentoBaumn();
        AirConditionerStrategy strategy = new ArCondicionadoVentoBaumnStrategy(airConditioner);

        strategy.turnOn();
        
        strategy.turnOff();
    }

    @Test
    void shouldIncreaseTemperature() {
        ArCondicionadoVentoBaumn airConditioner = new ArCondicionadoVentoBaumn();
        AirConditionerStrategy strategy = new ArCondicionadoVentoBaumnStrategy(airConditioner);

        strategy.turnOn();
        int initialTemperature = airConditioner.getTemperatura();
        strategy.increaseTemperature();

        assertEquals(initialTemperature + 1, airConditioner.getTemperatura());
    }

    @Test
    void shouldDecreaseTemperature() {
        ArCondicionadoVentoBaumn airConditioner = new ArCondicionadoVentoBaumn();
        AirConditionerStrategy strategy = new ArCondicionadoVentoBaumnStrategy(airConditioner);

        strategy.turnOn();
        int initialTemperature = airConditioner.getTemperatura();
        strategy.decreaseTemperature();

        assertEquals(initialTemperature - 1, airConditioner.getTemperatura());
    }

    @Test
    void shouldDefineTemperature() {
        ArCondicionadoVentoBaumn airConditioner = new ArCondicionadoVentoBaumn();
        AirConditionerStrategy strategy = new ArCondicionadoVentoBaumnStrategy(airConditioner);

        strategy.turnOn();
        int targetTemperature = 25;
        strategy.defineTemperature(targetTemperature);

        assertEquals(targetTemperature, airConditioner.getTemperatura());
    }

    @Test
    void shouldDefineTemperatureToHigherValue() {
        ArCondicionadoVentoBaumn airConditioner = new ArCondicionadoVentoBaumn();
        AirConditionerStrategy strategy = new ArCondicionadoVentoBaumnStrategy(airConditioner);

        strategy.turnOn();
        int targetTemperature = airConditioner.getTemperatura() + 10;
        strategy.defineTemperature(targetTemperature);

        assertEquals(targetTemperature, airConditioner.getTemperatura());
    }

    @Test
    void shouldDefineTemperatureToLowerValue() {
        ArCondicionadoVentoBaumn airConditioner = new ArCondicionadoVentoBaumn();
        AirConditionerStrategy strategy = new ArCondicionadoVentoBaumnStrategy(airConditioner);

        strategy.turnOn();
        int targetTemperature = airConditioner.getTemperatura() - 5;
        strategy.defineTemperature(targetTemperature);

        assertEquals(targetTemperature, airConditioner.getTemperatura());
    }
}
