package br.furb.problema03.factories;

import br.furb.analise.algoritmos.LampadaPhellipes;
import br.furb.analise.algoritmos.LampadaShoyuMi;
import br.furb.problema03.enums.IntelligentLampEnum;
import br.furb.problema03.strategies.lamps.LampStrategy;
import br.furb.problema03.strategies.lamps.LampadaPhellipesStrategy;
import br.furb.problema03.strategies.lamps.LampadaShoyuMiStrategy;

public class LampFactory {
    public static LampStrategy createIntelligentLampFactory(IntelligentLampEnum intelligentLampEnum) throws IllegalArgumentException {
        if (intelligentLampEnum == null) {
            throw new IllegalArgumentException("O tipo de lâmpada inteligente não pode ser nulo");
        }

        return switch (intelligentLampEnum) {
            case SHOYUMI -> new LampadaShoyuMiStrategy(new LampadaShoyuMi());
            case PHELLIPES -> new LampadaPhellipesStrategy(new LampadaPhellipes());
        };
    }
}
