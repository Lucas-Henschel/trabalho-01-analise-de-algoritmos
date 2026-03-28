package br.furb.problema03.facades.blinds;

import br.furb.analise.algoritmos.PersianaNatLight;
import br.furb.analise.algoritmos.PersianaSolarius;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class IntelligentBlindsFacadeeTest {

    private PersianaNatLight natLight;
    private PersianaSolarius solarius;
    private IntelligentBlindsFacadee facade;

    @BeforeEach
    void setup() {
        natLight = mock(PersianaNatLight.class);
        solarius = mock(PersianaSolarius.class);
        facade = new IntelligentBlindsFacadee(natLight, solarius);
    }

    @Test
    void shouldOpenBlindsNormally() throws Exception {
        facade.open();

        verify(solarius).subirPersiana();
        verify(natLight).subirPalheta();
        verify(natLight, never()).abrirPalheta();
    }

    @Test
    void shouldOpenBlindsWhenNatLightThrowsException() throws Exception {
        doThrow(new Exception()).doNothing().when(natLight).subirPalheta();
        facade.open();

        verify(solarius).subirPersiana();
        verify(natLight).abrirPalheta();
        verify(natLight, times(2)).subirPalheta();
    }

    @Test
    void shouldCloseBlinds() throws Exception {
        when(natLight.estaPalhetaErguida()).thenReturn(true);
        when(natLight.estaPalhetaAberta()).thenReturn(true);

        facade.close();

        verify(solarius).descerPersiana();
        verify(natLight).descerPalheta();
        verify(natLight).fecharPalheta();
    }

    @Test
    void shouldOpenNatLightBlade() {
        facade.openNatLightBlade();

        verify(natLight).abrirPalheta();
    }

    @Test
    void shouldCloseNatLightBladeNormally() throws Exception {
        facade.closeNatLightBlade();

        verify(natLight).fecharPalheta();
        verify(natLight, never()).descerPalheta();
    }

    @Test
    void shouldCloseNatLightBladeWhenExceptionOccurs() throws Exception {
        doThrow(new Exception())
                .doNothing()
                .when(natLight).fecharPalheta();

        facade.closeNatLightBlade();

        verify(natLight).descerPalheta();
        verify(natLight, times(2)).fecharPalheta();
    }
}