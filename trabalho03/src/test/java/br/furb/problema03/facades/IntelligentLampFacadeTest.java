package br.furb.problema03.facades;

import br.furb.problema03.enums.IntelligentLampEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntelligentLampFacadeTest {
    private List<IntelligentLampEnum> lampTypes;

    @BeforeEach
    void setUp() {
        lampTypes = List.of(
            IntelligentLampEnum.SHOYUMI,
            IntelligentLampEnum.PHELLIPES
        );
    }

    @Test
    void shouldTurnOnAllLamps() {
        IntelligentLampFacade facade = new IntelligentLampFacade(lampTypes);

        assertDoesNotThrow(facade::turnOnAll);
    }

    @Test
    void shouldTurnOffAllLamps() {
        IntelligentLampFacade facade = new IntelligentLampFacade(lampTypes);

        facade.turnOnAll();
        assertDoesNotThrow(facade::turnOffAll);
    }

    @Test
    void shouldWorkWithSingleLamp() {
        List<IntelligentLampEnum> lampType = List.of(
            IntelligentLampEnum.SHOYUMI
        );

        IntelligentLampFacade facade = new IntelligentLampFacade(lampType);

        assertDoesNotThrow(() -> {
            facade.turnOnAll();
            facade.turnOffAll();
        });
    }

    @Test
    void shouldHandleCompleteFlowForMultipleLamps() {
        IntelligentLampFacade facade = new IntelligentLampFacade(lampTypes);

        assertDoesNotThrow(() -> {
            facade.turnOnAll();
            facade.turnOffAll();
            facade.turnOnAll();
            facade.turnOffAll();
        });
    }

    @Test
    void constructorShouldThrowWhenLampListIsNull() {
        assertThrows(NullPointerException.class, () -> new IntelligentLampFacade(null));
    }

    @Test
    void constructorShouldThrowWhenLampListContainsNull() {
        List<IntelligentLampEnum> lampTypesWithNull = new ArrayList<>();
        lampTypesWithNull.add(IntelligentLampEnum.SHOYUMI);
        lampTypesWithNull.add(null);

        assertThrows(IllegalArgumentException.class, () -> new IntelligentLampFacade(lampTypesWithNull));
    }

    @Test
    void shouldNotThrowWhenLampListIsEmpty() {
        IntelligentLampFacade facade = new IntelligentLampFacade(List.of());

        assertDoesNotThrow(facade::turnOnAll);
        assertDoesNotThrow(facade::turnOffAll);
    }
}
