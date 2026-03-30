package br.furb.problema03.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.furb.problema03.enums.IntelligentAirConditionerEnum;
import br.furb.problema03.strategies.airConditioner.AirConditionerStrategy;
import br.furb.problema03.strategies.airConditioner.ArCondicionadoGellaKazaStrategy;
import br.furb.problema03.strategies.airConditioner.ArCondicionadoVentoBaumnStrategy;

public class AirConditionerFactoryTest {

    @Test
    void shouldReturnGellaKazaStrategy() {
        AirConditionerStrategy gellaKazaStrategy = AirConditionerFactory.createAirConditionerStrategy(IntelligentAirConditionerEnum.GELLAKAZA);

        assertInstanceOf(ArCondicionadoGellaKazaStrategy.class, gellaKazaStrategy);
    }

    @Test
    void shouldReturnVentoBaumnStrategy() {
        AirConditionerStrategy ventoBaumnStrategy = AirConditionerFactory.createAirConditionerStrategy(IntelligentAirConditionerEnum.VENTOBAUMN);

        assertInstanceOf(ArCondicionadoVentoBaumnStrategy.class, ventoBaumnStrategy);
    }

    @Test
    void shouldThrowWhenAirConditionerTypeIsNull() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> AirConditionerFactory.createAirConditionerStrategy(null)
        );

        assertEquals("Tipo de ar-condicionado inválido não pode ser null", exception.getMessage());
    }
}
