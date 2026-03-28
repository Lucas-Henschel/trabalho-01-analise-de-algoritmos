package br.furb.problema03.facades;

import br.furb.analise.algoritmos.PersianaNatLight;
import br.furb.analise.algoritmos.PersianaSolarius;
import br.furb.problema03.enums.IntelligentBlindEnum;
import br.furb.problema03.strategies.blinds.BlindStrategy;
import br.furb.problema03.strategies.blinds.PersianaNatLightStrategy;
import br.furb.problema03.strategies.blinds.PersianaSolariusStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntelligentBlindsFacadeTest {
    private PersianaSolarius sharedSolarius;
    private PersianaNatLight sharedNatLight;
    private BlindStrategy solariusBlinds;

    @BeforeEach
    void setUp() {
        sharedSolarius = new PersianaSolarius();
        sharedNatLight = new PersianaNatLight();
        solariusBlinds = new PersianaSolariusStrategy(sharedSolarius);
    }

    @Test
    void solariusOpenCloseFlow() {
        assertTrue(sharedSolarius.estaAberta());

        solariusBlinds.close();
        assertFalse(sharedSolarius.estaAberta());

        solariusBlinds.open();
        assertTrue(sharedSolarius.estaAberta());
    }

    @Test
    void natLightOpenCloseFlow() {
        PersianaNatLight shared = new PersianaNatLight();
        BlindStrategy nat = new PersianaNatLightStrategy(shared);

        nat.close();
        assertFalse(shared.estaPalhetaErguida());
        assertFalse(shared.estaPalhetaAberta());

        nat.open();
        assertTrue(shared.estaPalhetaAberta());
        assertTrue(shared.estaPalhetaErguida());
    }

    @Test
    void facadeOpenAllAndCloseAll() {
        List<IntelligentBlindEnum> blinds = List.of(IntelligentBlindEnum.NAT_LIGHT, IntelligentBlindEnum.SOLARIUS);
        IntelligentBlindFacade facade = new IntelligentBlindFacade(blinds);

        facade.closeAll();
        facade.openAll();

        assertDoesNotThrow(facade::openAll);
        assertDoesNotThrow(facade::closeAll);
    }
}
