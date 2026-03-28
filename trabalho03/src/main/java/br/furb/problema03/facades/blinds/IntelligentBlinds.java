package br.furb.problema03.facades.blinds;

public class IntelligentBlinds {
    private final BlindsStrategy strategy;

    public IntelligentBlinds(BlindsStrategy strategy) {
        this.strategy = strategy;
    }

    public void open() throws Exception {
        strategy.open();
    }

    public void close() throws Exception {
        strategy.close();
    }
}
