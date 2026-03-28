package br.furb.problema03.strategies.lamps;

import br.furb.analise.algoritmos.LampadaPhellipes;
import br.furb.problema03.exceptions.LampOperationException;
import br.furb.problema03.strategies.lamps.fakes.CloseFailurePhellipes;
import br.furb.problema03.strategies.lamps.fakes.OpenFailurePhellipes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LampadaPhellipesStrategyTest {
    @Test
    void shouldTurnOnPhellipesLamp() {
        LampadaPhellipes lamp = new LampadaPhellipes();
        LampStrategy strategy = new LampadaPhellipesStrategy(lamp);

        strategy.turnOn();

        assertEquals(100, lamp.getIntensidade());
    }

    @Test
    void shouldTurnOffPhellipesLampAfterTurnOn() {
        LampadaPhellipes lamp = new LampadaPhellipes();
        LampStrategy strategy = new LampadaPhellipesStrategy(lamp);

        strategy.turnOn();
        strategy.turnOff();

        assertEquals(0, lamp.getIntensidade());
    }

    @Test
    void shouldThrowLampOperationExceptionWhenTurnOnFails() {
        IllegalArgumentException exception = new IllegalArgumentException("Falha ao ligar a lampada");
        OpenFailurePhellipes lamp = new OpenFailurePhellipes(exception);

        LampStrategy strategy = new LampadaPhellipesStrategy(lamp);
        LampOperationException thrown = assertThrows(LampOperationException.class, strategy::turnOn);

        assertEquals("Não foi possível ligar a lâmpada Phellipes", thrown.getMessage());
        assertSame(exception, thrown.getCause());

        assertTrue(lamp.getIntensityCalled);
        assertTrue(lamp.setIntensityCalled);
        assertEquals(100, lamp.setIntensityValue);
    }

    @Test
    void shouldThrowLampOperationExceptionWhenTurnOffFails() {
        IllegalArgumentException exception = new IllegalArgumentException("Falha ao desligar a lampada");
        CloseFailurePhellipes lamp = new CloseFailurePhellipes(exception);

        LampStrategy strategy = new LampadaPhellipesStrategy(lamp);
        LampOperationException thrown = assertThrows(LampOperationException.class, strategy::turnOff);

        assertEquals("Não foi possível desligar a lâmpada Phellipes", thrown.getMessage());
        assertSame(exception, thrown.getCause());

        assertTrue(lamp.getIntensityCalled);
        assertTrue(lamp.setIntensityCalled);
        assertEquals(0, lamp.setIntensityValue);
    }
}

