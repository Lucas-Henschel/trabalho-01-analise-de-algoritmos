package br.furb.problema03.strategies.lamps.fakes;

import br.furb.analise.algoritmos.LampadaPhellipes;

public class OpenFailurePhellipes extends LampadaPhellipes {
    private final IllegalArgumentException exception;
    public boolean getIntensityCalled;
    public boolean setIntensityCalled;
    public int setIntensityValue;

    public OpenFailurePhellipes(IllegalArgumentException exception) {
        this.exception = exception;
    }

    @Override
    public int getIntensidade() {
        getIntensityCalled = true;
        return 0;
    }

    @Override
    public void setIntensidade(int intensidade) {
        setIntensityCalled = true;
        setIntensityValue = intensidade;
        throw exception;
    }
}

