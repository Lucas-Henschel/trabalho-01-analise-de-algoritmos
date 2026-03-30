package br.furb.problema03.facades;

import br.furb.problema03.enums.IntelligentAirConditionerEnum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntelligentAirConditionerFacadeTest {

    private List<IntelligentAirConditionerEnum> airConditionerTypes;

    @BeforeEach
    void setUp() {
        airConditionerTypes = List.of(
                IntelligentAirConditionerEnum.GELLAKAZA,
                IntelligentAirConditionerEnum.VENTOBAUMN
        );
    }
    @Test
    void shouldTurnOnAllAirConditioners() {
        IntelligentAirConditionerFacade facade = new IntelligentAirConditionerFacade(airConditionerTypes);
        assertDoesNotThrow(facade::turnOnAll);
    }

    @Test
    void shouldTurnOffAllAirConditioners() {
        IntelligentAirConditionerFacade facade = new IntelligentAirConditionerFacade(airConditionerTypes);  
        facade.turnOnAll();
        assertDoesNotThrow(facade::turnOffAll);
    }

    @Test
    void shouldIncreaseTemperatureForAllAirConditioners() {
        IntelligentAirConditionerFacade facade = new IntelligentAirConditionerFacade(airConditionerTypes);

        facade.turnOnAll();
        assertDoesNotThrow(facade::increaseTemperatureAll);
    }

    @Test
    void shouldDecreaseTemperatureForAllAirConditioners() {
        IntelligentAirConditionerFacade facade = new IntelligentAirConditionerFacade(airConditionerTypes);

        facade.turnOnAll();
        assertDoesNotThrow(facade::decreaseTemperatureAll);
    }

    @Test
    void shouldDefineTemperatureForAllAirConditioners() {
        IntelligentAirConditionerFacade facade = new IntelligentAirConditionerFacade(airConditionerTypes);

        facade.turnOnAll();
        assertDoesNotThrow(() -> facade.defineTemperatureAll(22));
    }

    @Test
    void shouldWorkWithSingleAirConditioner() {
        List<IntelligentAirConditionerEnum> airConditionerType = List.of(
                IntelligentAirConditionerEnum.GELLAKAZA
        );
        IntelligentAirConditionerFacade facade = new IntelligentAirConditionerFacade(airConditionerType);

        assertDoesNotThrow(() -> {
            facade.turnOnAll();
            facade.increaseTemperatureAll();
            facade.decreaseTemperatureAll();
            facade.defineTemperatureAll(20);
            facade.turnOffAll();
        });
    }

    @Test
    void shouldHandleCompleteFlowForMultipleAirConditioners() {
        IntelligentAirConditionerFacade facade = new IntelligentAirConditionerFacade(airConditionerTypes);

        assertDoesNotThrow(() -> {
            facade.turnOnAll();
            facade.increaseTemperatureAll();
            facade.increaseTemperatureAll();
            facade.decreaseTemperatureAll();
            facade.defineTemperatureAll(18);
            facade.turnOffAll();
        });
    }
}
