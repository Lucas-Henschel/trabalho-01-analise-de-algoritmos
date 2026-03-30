package br.furb.problema03.factories;

import br.furb.analise.algoritmos.PersianaNatLight;
import br.furb.analise.algoritmos.PersianaSolarius;
import br.furb.problema03.enums.IntelligentBlindEnum;
import br.furb.problema03.strategies.blinds.BlindStrategy;
import br.furb.problema03.strategies.blinds.PersianaNatLightStrategy;
import br.furb.problema03.strategies.blinds.PersianaSolariusStrategy;

public class BlindFactory {
    public static BlindStrategy createBlindFactory(IntelligentBlindEnum intelligentBlindEnum) {
        if (intelligentBlindEnum == null) {
            throw new IllegalArgumentException("Tipo de persiana inválida não pode ser null");
        }

        switch (intelligentBlindEnum) {
            case SOLARIUS -> {
                PersianaSolarius solarius = new PersianaSolarius();
                return new PersianaSolariusStrategy(solarius);
            }
            case NAT_LIGHT -> {
                PersianaNatLight natLight = new PersianaNatLight();
                return new PersianaNatLightStrategy(natLight);
            }
        };

        throw new IllegalArgumentException("Tipo de persiana inválida");
    }
}
