package br.furb.problema03.strategies.lamps;

import br.furb.analise.algoritmos.LampadaShoyuMi;
import br.furb.problema03.exceptions.LampOperationException;

public class LampadaShoyuMiStrategy implements LampStrategy{
    private final LampadaShoyuMi lampadaShoyuMi;

    public LampadaShoyuMiStrategy(LampadaShoyuMi lampadaShoyuMi) {
        this.lampadaShoyuMi = lampadaShoyuMi;
    }

    @Override
    public void turnOn() {
        try {
            if (!lampadaShoyuMi.estaLigada()) {
                lampadaShoyuMi.ligar();
            }
        } catch (Exception exception) {
            throw new LampOperationException("Não foi possível ligar a lâmpada ShoyuMi", exception);
        }
    }

    @Override
    public void turnOff() {
        try {
            if(lampadaShoyuMi.estaLigada()) {
                lampadaShoyuMi.desligar();
            }
        } catch (Exception exception) {
            throw new LampOperationException("Não foi possível desligar a lâmpada ShoyuMi", exception);
        }
    }
}
