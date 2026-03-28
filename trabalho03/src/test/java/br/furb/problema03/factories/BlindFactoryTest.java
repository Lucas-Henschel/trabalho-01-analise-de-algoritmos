package br.furb.problema03.factories;

import br.furb.problema03.strategies.blinds.BlindStrategy;

import br.furb.problema03.strategies.blinds.PersianaNatLightStrategy;
import br.furb.problema03.strategies.blinds.PersianaSolariusStrategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlindFactoryTest {
    @Test
    void shouldReturnPersianaSolariusStrategy() {
        BlindStrategy solariusBlind = BlindFactory.createSolariusBlind();

        assertInstanceOf(PersianaSolariusStrategy.class, solariusBlind);
    }

    @Test
    void shouldReturnNatLightBlindStrategy() {
        BlindStrategy natLightBlind = BlindFactory.createNatLightBlind();

        assertInstanceOf(PersianaNatLightStrategy.class, natLightBlind);
    }
}
