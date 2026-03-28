package br.furb.problema03.factories;

import br.furb.analise.algoritmos.PersianaNatLight;
import br.furb.analise.algoritmos.PersianaSolarius;
import br.furb.problema03.strategies.blinds.BlindStrategy;
import br.furb.problema03.strategies.blinds.PersianaNatLightStrategy;
import br.furb.problema03.strategies.blinds.PersianaSolariusStrategy;

public class BlindFactory {
    public static BlindStrategy createSolariusBlind() {
        PersianaSolarius solarius = new PersianaSolarius();
        return new PersianaSolariusStrategy(solarius);
    }

    public static PersianaNatLightStrategy createNatLightBlind() {
        PersianaNatLight natLight = new PersianaNatLight();
        return new PersianaNatLightStrategy(natLight);
    }
}
