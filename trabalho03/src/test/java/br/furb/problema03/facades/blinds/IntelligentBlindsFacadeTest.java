package br.furb.problema03.facades.blinds;

import br.furb.analise.algoritmos.PersianaNatLight;
import br.furb.analise.algoritmos.PersianaSolarius;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntelligentBlindsFacadeTest {
    private PersianaSolarius sharedSolarius;
    private PersianaNatLight sharedNatLight;
    private IntelligentBlinds solariusBlinds;
    private IntelligentBlinds natLightBlinds;

    @BeforeEach
    void setUp() {
        sharedSolarius = new PersianaSolarius();
        sharedNatLight = new PersianaNatLight();
        solariusBlinds = new IntelligentBlinds(new PersianaSolariusStrategy(sharedSolarius));
        natLightBlinds = new IntelligentBlinds(new PersianaNatLightStrategy(sharedNatLight));
    }

    @Test
    void solariusOpenCloseFlow() throws Exception {
        assertTrue(sharedSolarius.estaAberta());

        solariusBlinds.close();
        assertFalse(sharedSolarius.estaAberta());

        solariusBlinds.open();
        assertTrue(sharedSolarius.estaAberta());
    }

    @Test
    void natLightOpenCloseFlow() throws Exception {
        PersianaNatLight shared = new PersianaNatLight();
        IntelligentBlinds nat = new IntelligentBlinds(new PersianaNatLightStrategy(shared));

        nat.close();
        assertFalse(shared.estaPalhetaErguida());
        assertFalse(shared.estaPalhetaAberta());

        nat.open();
        assertTrue(shared.estaPalhetaAberta());
        assertTrue(shared.estaPalhetaErguida());
    }

    @Test
    void facadeOpenAllAndCloseAll() throws Exception {
        IntelligentBlindsFacade facade = new IntelligentBlindsFacade(solariusBlinds, natLightBlinds);

        facade.closeAll();
        facade.openAll();

        assertDoesNotThrow(facade::openAll);
        assertDoesNotThrow(facade::closeAll);
    }
}
