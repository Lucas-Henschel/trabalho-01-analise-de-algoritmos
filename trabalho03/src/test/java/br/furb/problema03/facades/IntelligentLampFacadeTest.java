package br.furb.problema03.facades;

import br.furb.analise.algoritmos.LampadaPhellipes;
import br.furb.analise.algoritmos.LampadaShoyuMi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class IntelligentLampFacadeTest {
    @Mock
    private LampadaShoyuMi lampadaShoyuMi;

    @Mock
    private LampadaPhellipes lampadaPhellipes;

    private IntelligentLampFacade intelligentLampFacade;

    @BeforeEach
    void setUp() {
        intelligentLampFacade = new IntelligentLampFacade(lampadaShoyuMi, lampadaPhellipes);
    }

    @Test
    void turnOnShouldTurnOnShoyuMiAndSetPhellipesToMaxIntensityInOrder() {
        intelligentLampFacade.turnOn();

        InOrder inOrder = Mockito.inOrder(lampadaShoyuMi, lampadaPhellipes);
        inOrder.verify(lampadaShoyuMi).ligar();
        inOrder.verify(lampadaPhellipes).setIntensidade(100);

        verifyNoMoreInteractions(lampadaShoyuMi, lampadaPhellipes);
    }

    @Test
    void turnOffShouldTurnOffShoyuMiAndSetPhellipesToZeroIntensityInOrder() {
        intelligentLampFacade.turnOff();

        InOrder inOrder = Mockito.inOrder(lampadaShoyuMi, lampadaPhellipes);
        inOrder.verify(lampadaShoyuMi).desligar();
        inOrder.verify(lampadaPhellipes).setIntensidade(0);

        verifyNoMoreInteractions(lampadaShoyuMi, lampadaPhellipes);
    }

    @Test
    void turnOnShouldPropagateExceptionWhenShoyuMiFails() {
        RuntimeException exception = new RuntimeException("Falha ao ligar ShoyuMi");
        doThrow(exception).when(lampadaShoyuMi).ligar();

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> intelligentLampFacade.turnOn());

        verify(lampadaShoyuMi).ligar();
        verifyNoMoreInteractions(lampadaShoyuMi, lampadaPhellipes);

        Assertions.assertSame(exception, thrown);
    }

    @Test
    void turnOnShouldPropagateExceptionWhenPhellipesFails() {
        RuntimeException exception = new RuntimeException("Falha ao ajustar intensidade");
        doThrow(exception).when(lampadaPhellipes).setIntensidade(100);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> intelligentLampFacade.turnOn());

        verify(lampadaShoyuMi).ligar();
        verify(lampadaPhellipes).setIntensidade(100);
        verifyNoMoreInteractions(lampadaShoyuMi, lampadaPhellipes);

        Assertions.assertSame(exception, thrown);
    }

    @Test
    void turnOffShouldPropagateExceptionWhenShoyuMiFails() {
        RuntimeException exception = new RuntimeException("Falha ao desligar ShoyuMi");
        doThrow(exception).when(lampadaShoyuMi).desligar();

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> intelligentLampFacade.turnOff());

        verify(lampadaShoyuMi).desligar();
        verifyNoMoreInteractions(lampadaShoyuMi, lampadaPhellipes);

        Assertions.assertSame(exception, thrown);
    }

    @Test
    void turnOffShouldPropagateExceptionWhenPhellipesFails() {
        RuntimeException exception = new RuntimeException("Falha ao ajustar intensidade");
        doThrow(exception).when(lampadaPhellipes).setIntensidade(0);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> intelligentLampFacade.turnOff());

        verify(lampadaShoyuMi).desligar();
        verify(lampadaPhellipes).setIntensidade(0);
        verifyNoMoreInteractions(lampadaShoyuMi, lampadaPhellipes);

        Assertions.assertSame(exception, thrown);
    }
}