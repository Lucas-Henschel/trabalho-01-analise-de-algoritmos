package br.furb.problema03.strategies.lamps;

import br.furb.analise.algoritmos.LampadaPhellipes;
import br.furb.problema03.exceptions.LampOperationException;

public class LampadaPhellipesStrategy implements LampStrategy {
    private final LampadaPhellipes lampadaPhellipes;

    public LampadaPhellipesStrategy(LampadaPhellipes lampadaPhellipes) {
        this.lampadaPhellipes = lampadaPhellipes;
    }

    @Override
    public void turnOn() {
        try{
            if (lampadaPhellipes.getIntensidade() != 100) {
                lampadaPhellipes.setIntensidade(100);
            }
        } catch (Exception e) {
            throw new LampOperationException("Não foi possível ligar a lâmpada Phellipes", e);
        }

    }

    @Override
    public void turnOff() {
        try {
            if (lampadaPhellipes.getIntensidade() != 0) {
                lampadaPhellipes.setIntensidade(0);
            }
        } catch (Exception e) {
            throw new LampOperationException("Não foi possível desligar a lâmpada Phellipes", e);
        }
    }
}
