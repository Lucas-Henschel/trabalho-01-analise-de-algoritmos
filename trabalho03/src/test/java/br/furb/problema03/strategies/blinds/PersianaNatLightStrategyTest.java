package br.furb.problema03.strategies.blinds;

import br.furb.analise.algoritmos.PersianaNatLight;
import br.furb.problema03.exceptions.BlindOperationException;
import br.furb.problema03.strategies.blinds.fakes.CloseFailureNatLight;
import br.furb.problema03.strategies.blinds.fakes.OpenFailureNatLight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersianaNatLightStrategyTest {
    @Test
    void shouldCloseNatLightBlind() {
        PersianaNatLight blind = new PersianaNatLight();
        BlindStrategy strategy = new PersianaNatLightStrategy(blind);

        strategy.close();

        assertFalse(blind.estaPalhetaAberta());
        assertFalse(blind.estaPalhetaErguida());
    }

    @Test
    void shouldOpenNatLightBlindAfterClose() {
        PersianaNatLight blind = new PersianaNatLight();
        BlindStrategy strategy = new PersianaNatLightStrategy(blind);

        strategy.close();
        strategy.open();

        assertTrue(blind.estaPalhetaAberta());
        assertTrue(blind.estaPalhetaErguida());
    }

    @Test
    void shouldThrowBlindOperationExceptionWhenOpenFails() {
        Exception exception = new Exception("Falha ao subir palheta");
        OpenFailureNatLight blind = new OpenFailureNatLight(exception);

        BlindStrategy strategy = new PersianaNatLightStrategy(blind);
        BlindOperationException thrown = assertThrows(BlindOperationException.class, strategy::open);

        assertEquals("Não foi possível abrir as persianas NatLight", thrown.getMessage());
        assertSame(exception, thrown.getCause());

        assertTrue(blind.openBladeCalled);
        assertTrue(blind.raiseBladeCalled);
    }

    @Test
    void shouldThrowBlindOperationExceptionWhenCloseFails() {
        Exception exception = new Exception("Falha ao fechar palheta");
        CloseFailureNatLight blind = new CloseFailureNatLight(exception);

        BlindStrategy strategy = new PersianaNatLightStrategy(blind);
        BlindOperationException thrown = assertThrows(BlindOperationException.class, strategy::close);

        assertEquals("Não foi possível fechar as persianas NatLight", thrown.getMessage());
        assertSame(exception, thrown.getCause());

        assertTrue(blind.lowerBladeCalled);
        assertTrue(blind.closeBladeCalled);
    }
}
