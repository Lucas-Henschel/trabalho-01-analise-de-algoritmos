package br.furb.problema03.facades;

import java.util.Objects;

public class RoomModeFacade {
    private static final int WORK_MODE_TEMPERATURE = 25;

    private final IntelligentLampFacade lampFacade;
    private final IntelligentBlindFacade blindFacade;
    private final IntelligentAirConditionerFacade airConditionerFacade;

    public RoomModeFacade(
        IntelligentLampFacade lampFacade,
        IntelligentBlindFacade blindFacade,
        IntelligentAirConditionerFacade airConditionerFacade
    ) {
        this.lampFacade = Objects.requireNonNull(lampFacade);
        this.blindFacade = Objects.requireNonNull(blindFacade);
        this.airConditionerFacade = Objects.requireNonNull(airConditionerFacade);
    }

    public void activateSleepMode() {
        airConditionerFacade.turnOffAll();
        lampFacade.turnOffAll();
        blindFacade.closeAll();
    }

    public void activateWorkMode() {
        lampFacade.turnOnAll();
        airConditionerFacade.turnOnAll();
        airConditionerFacade.defineTemperatureAll(WORK_MODE_TEMPERATURE);
        blindFacade.openAll();
    }
}
