package br.furb.problema03.facades.blinds;

import br.furb.analise.algoritmos.PersianaNatLight;
import br.furb.analise.algoritmos.PersianaSolarius;

public class BlindsFactory {
    public static IntelligentBlinds createSolariusBlinds() {
        PersianaSolarius solarius = new PersianaSolarius();
        return new IntelligentBlinds(new PersianaSolariusStrategy(solarius));
    }

    public static IntelligentBlinds createNatLightBlinds() {
        PersianaNatLight natLight = new PersianaNatLight();
        return new IntelligentBlinds(new PersianaNatLightStrategy(natLight));
    }
}
