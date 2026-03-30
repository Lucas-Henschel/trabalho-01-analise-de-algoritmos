package br.furb.problema03.factories;

import br.furb.problema03.enums.IntelligentLampEnum;
import br.furb.problema03.strategies.lamps.LampStrategy;
import br.furb.problema03.strategies.lamps.LampadaPhellipesStrategy;
import br.furb.problema03.strategies.lamps.LampadaShoyuMiStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LampFactoryTest {

    @Test
    void shouldCreateShoyuMiStrategy() {
        LampStrategy lampStrategy = LampFactory.createIntelligentLampFactory(IntelligentLampEnum.SHOYUMI);

        assertInstanceOf(LampadaShoyuMiStrategy.class, lampStrategy);
    }

    @Test
    void shouldCreatePhellipesStrategy() {
        LampStrategy lampStrategy = LampFactory.createIntelligentLampFactory(IntelligentLampEnum.PHELLIPES);

        assertInstanceOf(LampadaPhellipesStrategy.class, lampStrategy);
    }

    @Test
    void shouldCreateNewInstanceOnEachFactoryCall() {
        LampStrategy first = LampFactory.createIntelligentLampFactory(IntelligentLampEnum.SHOYUMI);
        LampStrategy second = LampFactory.createIntelligentLampFactory(IntelligentLampEnum.SHOYUMI);

        assertNotSame(first, second);
    }

    @Test
    void shouldThrowWhenLampTypeIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> LampFactory.createIntelligentLampFactory(null));

        assertEquals("O tipo de lâmpada inteligente não pode ser nulo", exception.getMessage());
    }
}

