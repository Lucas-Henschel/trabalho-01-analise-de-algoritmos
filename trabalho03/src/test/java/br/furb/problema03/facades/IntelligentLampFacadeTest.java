package br.furb.problema03.facades;

import br.furb.analise.algoritmos.LampadaPhellipes;
import br.furb.analise.algoritmos.LampadaShoyuMi;
import br.furb.problema03.strategies.lamps.LampStrategy;
import br.furb.problema03.strategies.lamps.LampadaPhellipesStrategy;
import br.furb.problema03.strategies.lamps.LampadaShoyuMiStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntelligentLampFacadeTest {
    private LampadaShoyuMi shoyuMiDevice;
    private LampadaPhellipes phellipesDevice;
    private LampStrategy shoyuMiStrategy;
    private LampStrategy phellipesStrategy;

    @BeforeEach
    void setUp() {
        shoyuMiDevice = new LampadaShoyuMi();
        phellipesDevice = new LampadaPhellipes();
        shoyuMiStrategy = new LampadaShoyuMiStrategy(shoyuMiDevice);
        phellipesStrategy = new LampadaPhellipesStrategy(phellipesDevice);
    }

    @Test
    void shoyuMiTurnOnTurnOffFlow() {
        assertFalse(shoyuMiDevice.estaLigada());

        shoyuMiStrategy.turnOn();
        assertTrue(shoyuMiDevice.estaLigada());

        shoyuMiStrategy.turnOff();
        assertFalse(shoyuMiDevice.estaLigada());
    }

    @Test
    void phellipesTurnOnTurnOffFlow() {
        phellipesStrategy.turnOff();
        assertEquals(0, phellipesDevice.getIntensidade());

        phellipesStrategy.turnOn();
        assertEquals(100, phellipesDevice.getIntensidade());
    }

    @Test
    void facadeTurnOnAllAndTurnOffAll() {
        List<LampStrategy> lampStrategies = List.of(shoyuMiStrategy, phellipesStrategy);
        IntelligentLampFacade lampFacade = new IntelligentLampFacade(lampStrategies);

        lampFacade.turnOffAll();
        assertFalse(shoyuMiDevice.estaLigada());
        assertEquals(0, phellipesDevice.getIntensidade());

        lampFacade.turnOnAll();
        assertTrue(shoyuMiDevice.estaLigada());
        assertEquals(100, phellipesDevice.getIntensidade());

        assertDoesNotThrow(lampFacade::turnOnAll);
        assertDoesNotThrow(lampFacade::turnOffAll);
    }

    @Test
    void constructorShouldThrowWhenLampListIsNull() {
        assertThrows(NullPointerException.class, () -> new IntelligentLampFacade(null));
    }

    @Test
    void constructorShouldThrowWhenLampListContainsNull() {
        List<LampStrategy> lampStrategiesWithNull = new ArrayList<>();
        lampStrategiesWithNull.add(shoyuMiStrategy);
        lampStrategiesWithNull.add(null);

        assertThrows(NullPointerException.class, () -> new IntelligentLampFacade(lampStrategiesWithNull));
    }

    @Test
    void shouldNotThrowWhenLampListIsEmpty() {
        IntelligentLampFacade lampFacade = new IntelligentLampFacade(List.of());

        assertDoesNotThrow(lampFacade::turnOnAll);
        assertDoesNotThrow(lampFacade::turnOffAll);
    }

    @Test
    void constructorShouldCreateDefensiveCopyOfLampList() {
        CountingLampStrategy countingStrategy = new CountingLampStrategy();
        List<LampStrategy> mutableLampStrategies = new ArrayList<>();
        mutableLampStrategies.add(countingStrategy);

        IntelligentLampFacade lampFacade = new IntelligentLampFacade(mutableLampStrategies);
        mutableLampStrategies.clear();

        lampFacade.turnOnAll();
        lampFacade.turnOffAll();

        assertEquals(1, countingStrategy.turnOnCalls);
        assertEquals(1, countingStrategy.turnOffCalls);
    }

    private static class CountingLampStrategy implements LampStrategy {
        private int turnOnCalls;
        private int turnOffCalls;

        @Override
        public void turnOn() {
            turnOnCalls++;
        }

        @Override
        public void turnOff() {
            turnOffCalls++;
        }
    }
}