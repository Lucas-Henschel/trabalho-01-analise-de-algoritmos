package br.furb.problema03.strategies.airConditioner;

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArCondicionadoGellaKazaStrategyTest {
    @Test
    void shouldTurnOnGellaKazaAirConditioner() {
        ArCondicionadoGellaKaza airConditioner = new ArCondicionadoGellaKaza();
        AirConditionerStrategy strategy = new ArCondicionadoGellaKazaStrategy(airConditioner);

        strategy.turnOn();

        assertTrue(airConditioner.estaLigado());
    }

    @Test
    void shouldTurnOffGellaKazaAirConditioner() {
        ArCondicionadoGellaKaza airConditioner = new ArCondicionadoGellaKaza();
        AirConditionerStrategy strategy = new ArCondicionadoGellaKazaStrategy(airConditioner);

        strategy.turnOn();
        strategy.turnOff();

        assertFalse(airConditioner.estaLigado());
    }

    @Test
    void shouldIncreaseTemperature() {
        ArCondicionadoGellaKaza airConditioner = new ArCondicionadoGellaKaza();
        AirConditionerStrategy strategy = new ArCondicionadoGellaKazaStrategy(airConditioner);

        int initialTemperature = airConditioner.getTemperatura();
        strategy.increaseTemperature();

        assertEquals(initialTemperature + 1, airConditioner.getTemperatura());
    }

    @Test
    void shouldDecreaseTemperature() {
        ArCondicionadoGellaKaza airConditioner = new ArCondicionadoGellaKaza();
        AirConditionerStrategy strategy = new ArCondicionadoGellaKazaStrategy(airConditioner);

        int initialTemperature = airConditioner.getTemperatura();
        strategy.decreaseTemperature();

        assertEquals(initialTemperature - 1, airConditioner.getTemperatura());
    }

    @Test
    void shouldDefineTemperatureWhenHigherThanCurrent() {
        ArCondicionadoGellaKaza airConditioner = new ArCondicionadoGellaKaza();
        AirConditionerStrategy strategy = new ArCondicionadoGellaKazaStrategy(airConditioner);

        int targetTemperature = airConditioner.getTemperatura() + 5;
        strategy.defineTemperature(targetTemperature);

        assertEquals(targetTemperature, airConditioner.getTemperatura());
    }

    @Test
    void shouldDefineTemperatureWhenLowerThanCurrent() {
        ArCondicionadoGellaKaza airConditioner = new ArCondicionadoGellaKaza();
        AirConditionerStrategy strategy = new ArCondicionadoGellaKazaStrategy(airConditioner);

        int targetTemperature = airConditioner.getTemperatura() - 3;
        strategy.defineTemperature(targetTemperature);

        assertEquals(targetTemperature, airConditioner.getTemperatura());
    }

    @Test
    void shouldDefineTemperatureWhenSameAsCurrent() {
        ArCondicionadoGellaKaza airConditioner = new ArCondicionadoGellaKaza();
        AirConditionerStrategy strategy = new ArCondicionadoGellaKazaStrategy(airConditioner);

        int currentTemperature = airConditioner.getTemperatura();
        strategy.defineTemperature(currentTemperature);

        assertEquals(currentTemperature, airConditioner.getTemperatura());
    }
}
