package br.furb.problema03.facades;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class RoomModeFacadeTest {
    @Mock
    private IntelligentLampFacade lampFacade;

    @Mock
    private IntelligentBlindFacade blindFacade;

    @Mock
    private IntelligentAirConditionerFacade airConditionerFacade;

    @Test
    void shouldActivateSleepMode() {
        RoomModeFacade facade = new RoomModeFacade(lampFacade, blindFacade, airConditionerFacade);

        facade.activateSleepMode();

        InOrder inOrder = inOrder(airConditionerFacade, lampFacade, blindFacade);
        inOrder.verify(airConditionerFacade).turnOffAll();
        inOrder.verify(lampFacade).turnOffAll();
        inOrder.verify(blindFacade).closeAll();

        verifyNoMoreInteractions(airConditionerFacade, lampFacade, blindFacade);
    }

    @Test
    void shouldActivateWorkMode() {
        RoomModeFacade facade = new RoomModeFacade(lampFacade, blindFacade, airConditionerFacade);

        facade.activateWorkMode();

        InOrder inOrder = inOrder(lampFacade, airConditionerFacade, blindFacade);
        inOrder.verify(lampFacade).turnOnAll();
        inOrder.verify(airConditionerFacade).turnOnAll();
        inOrder.verify(airConditionerFacade).defineTemperatureAll(25);
        inOrder.verify(blindFacade).openAll();

        verifyNoMoreInteractions(lampFacade, airConditionerFacade, blindFacade);
    }

    @Test
    void shouldExecuteModesWithRealFacades() {
        RoomModeFacade facade = new RoomModeFacade(lampFacade, blindFacade, airConditionerFacade);

        assertDoesNotThrow(() -> {
            facade.activateSleepMode();
            facade.activateWorkMode();
        });
    }
}
