package br.furb.problema03.factories;

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza;
import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn;
import br.furb.problema03.enums.IntelligentAirConditionerEnum;
import br.furb.problema03.strategies.airConditioner.AirConditionerStrategy;
import br.furb.problema03.strategies.airConditioner.ArCondicionadoGellaKazaStrategy;
import br.furb.problema03.strategies.airConditioner.ArCondicionadoVentoBaumnStrategy;

public class AirConditionerFactory {
    public static AirConditionerStrategy createAirConditionerStrategy(IntelligentAirConditionerEnum type) {
        if (type == null) {
            throw new IllegalArgumentException("Tipo de ar-condicionado inválido não pode ser null");
        }

        switch (type) {
            case GELLAKAZA -> {
                ArCondicionadoGellaKaza arCondicionadoGellaKaza = new ArCondicionadoGellaKaza();
                return new ArCondicionadoGellaKazaStrategy(arCondicionadoGellaKaza);
            }
            case VENTOBAUMN -> {
                ArCondicionadoVentoBaumn arCondicionadoVentoBaumn = new ArCondicionadoVentoBaumn();
                return new ArCondicionadoVentoBaumnStrategy(arCondicionadoVentoBaumn);
            }
        }
        
        throw new IllegalArgumentException("Tipo de ar-condicionado inválido");
    }
}
