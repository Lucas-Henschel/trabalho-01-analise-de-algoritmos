package br.furb.problema03.facades;

import br.furb.problema03.enums.IntelligentBlindEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class IntelligentBlindsFacadeTest {
    private List<IntelligentBlindEnum> blindTypes;

    @BeforeEach
    void setUp() {
        blindTypes = List.of(
            IntelligentBlindEnum.NAT_LIGHT,
            IntelligentBlindEnum.SOLARIUS
        );
    }

    @Test
    void shouldOpenAllBlinds() {
        IntelligentBlindFacade facade = new IntelligentBlindFacade(blindTypes);

        assertDoesNotThrow(facade::openAll);
    }

    @Test
    void shouldCloseAllBlinds() {
        IntelligentBlindFacade facade = new IntelligentBlindFacade(blindTypes);

        facade.openAll();
        assertDoesNotThrow(facade::closeAll);
    }

    @Test
    void shouldWorkWithSingleBlind() {
        List<IntelligentBlindEnum> blindType = List.of(
            IntelligentBlindEnum.NAT_LIGHT
        );

        IntelligentBlindFacade facade = new IntelligentBlindFacade(blindType);

        assertDoesNotThrow(() -> {
            facade.closeAll();
            facade.openAll();
        });
    }

    @Test
    void shouldHandleCompleteFlowForMultipleBlinds() {
        IntelligentBlindFacade facade = new IntelligentBlindFacade(blindTypes);

        assertDoesNotThrow(() -> {
            facade.closeAll();
            facade.openAll();
            facade.closeAll();
            facade.openAll();
        });
    }
}
