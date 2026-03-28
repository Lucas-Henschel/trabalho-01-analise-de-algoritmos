package br.furb.problema03.strategies.lamps.fakes;

import br.furb.analise.algoritmos.LampadaShoyuMi;

public class CloseFailureShoyuMi extends LampadaShoyuMi {
    private final RuntimeException exception;
    public boolean isOnCheckCalled;
    public boolean turnOffCalled;

    public CloseFailureShoyuMi(RuntimeException exception) {
        this.exception = exception;
    }

    @Override
    public boolean estaLigada() {
        isOnCheckCalled = true;
        return true;
    }

    @Override
    public void desligar() {
        turnOffCalled = true;
        throw exception;
    }
}

