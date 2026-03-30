package br.furb.problema03.strategies.lamps.fakes;

import br.furb.analise.algoritmos.LampadaShoyuMi;

public class OpenFailureShoyuMi extends LampadaShoyuMi {
    private final RuntimeException exception;
    public boolean isOnCheckCalled;
    public boolean turnOnCalled;

    public OpenFailureShoyuMi(RuntimeException exception) {
        this.exception = exception;
    }

    @Override
    public boolean estaLigada() {
        isOnCheckCalled = true;
        return false;
    }

    @Override
    public void ligar() {
        turnOnCalled = true;
        throw exception;
    }
}

