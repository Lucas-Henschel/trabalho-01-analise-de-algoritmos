package br.furb.problema03.strategies.lamps;

import br.furb.analise.algoritmos.LampadaShoyuMi;
import br.furb.problema03.exceptions.LampOperationException;
import br.furb.problema03.strategies.lamps.fakes.CloseFailureShoyuMi;
import br.furb.problema03.strategies.lamps.fakes.OpenFailureShoyuMi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LampadaShoyuMiStrategyTest {
    @Test
    void shouldTurnOnShoyuMiLamp() {
        LampadaShoyuMi lamp = new LampadaShoyuMi();
        LampStrategy strategy = new LampadaShoyuMiStrategy(lamp);

        strategy.turnOn();

        assertTrue(lamp.estaLigada());
    }

    @Test
    void shouldTurnOffShoyuMiLampAfterTurnOn() {
        LampadaShoyuMi lamp = new LampadaShoyuMi();
        LampStrategy strategy = new LampadaShoyuMiStrategy(lamp);

        strategy.turnOn();
        strategy.turnOff();

        assertFalse(lamp.estaLigada());
    }

    @Test
    void shouldThrowLampOperationExceptionWhenTurnOnFails() {
        RuntimeException exception = new RuntimeException("Falha ao ligar a lampada");
        OpenFailureShoyuMi lamp = new OpenFailureShoyuMi(exception);

        LampStrategy strategy = new LampadaShoyuMiStrategy(lamp);
        LampOperationException thrown = assertThrows(LampOperationException.class, strategy::turnOn);

        assertEquals("Não foi possível ligar a lâmpada ShoyuMi", thrown.getMessage());
        assertSame(exception, thrown.getCause());

        assertTrue(lamp.isOnCheckCalled);
        assertTrue(lamp.turnOnCalled);
    }

    @Test
    void shouldThrowLampOperationExceptionWhenTurnOffFails() {
        RuntimeException exception = new RuntimeException("Falha ao desligar a lampada");
        CloseFailureShoyuMi lamp = new CloseFailureShoyuMi(exception);

        LampStrategy strategy = new LampadaShoyuMiStrategy(lamp);
        LampOperationException thrown = assertThrows(LampOperationException.class, strategy::turnOff);

        assertEquals("Não foi possível desligar a lâmpada ShoyuMi", thrown.getMessage());
        assertSame(exception, thrown.getCause());

        assertTrue(lamp.isOnCheckCalled);
        assertTrue(lamp.turnOffCalled);
    }
}

