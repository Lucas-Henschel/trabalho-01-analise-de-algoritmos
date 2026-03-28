package br.furb.problema03.facades.blinds;

public class IntelligentBlindsFacade {
    private final IntelligentBlinds solariusBlinds;
    private final IntelligentBlinds natLightBlinds;

    public IntelligentBlindsFacade(IntelligentBlinds solariusBlinds, IntelligentBlinds natLightBlinds) {
        this.solariusBlinds = solariusBlinds;
        this.natLightBlinds = natLightBlinds;
    }

    public void openAll() throws Exception {
        solariusBlinds.open();
        natLightBlinds.open();
    }

    public void closeAll() throws Exception {
        solariusBlinds.close();
        natLightBlinds.close();
    }
}
