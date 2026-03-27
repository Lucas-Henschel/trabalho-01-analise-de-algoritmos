package br.furb.problema03.facades;

import br.furb.analise.algoritmos.LampadaPhellipes;
import br.furb.analise.algoritmos.LampadaShoyuMi;

public class IntelligentLampFacade {
    LampadaShoyuMi lampShoyuMi;
    LampadaPhellipes lampPhellipes;

    public IntelligentLampFacade(LampadaShoyuMi lampadaShoyuMi, LampadaPhellipes lampadaPhellipes) {
        this.lampShoyuMi = lampadaShoyuMi;
        this.lampPhellipes = lampadaPhellipes;
    }

    public void turnOn() {
        lampShoyuMi.ligar();
        lampPhellipes.setIntensidade(100);
    }

    public void turnOff() {
        lampShoyuMi.desligar();
        lampPhellipes.setIntensidade(0);
    }
}
