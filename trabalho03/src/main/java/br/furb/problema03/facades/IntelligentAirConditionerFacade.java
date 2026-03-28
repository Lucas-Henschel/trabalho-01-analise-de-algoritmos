package br.furb.problema03.facades;

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza;
import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn;

public class IntelligentAirConditionerFacade {
    private final ArCondicionadoGellaKaza arCondicionadoGellaKaza;
    private final ArCondicionadoVentoBaumn arCondicionadoVentoBaumn;

    public IntelligentAirConditionerFacade(ArCondicionadoGellaKaza arCondicionadoGellaKaza, ArCondicionadoVentoBaumn arCondicionadoVentoBaumn) {
        this.arCondicionadoGellaKaza = arCondicionadoGellaKaza;
        this.arCondicionadoVentoBaumn = arCondicionadoVentoBaumn;
    }

    public void turnOn() {
        arCondicionadoGellaKaza.ativar();
        arCondicionadoVentoBaumn.ligar();
    }

    public void turnOff() {
        arCondicionadoGellaKaza.desativar();
        arCondicionadoVentoBaumn.desligar();
    }

    public void increaseTemperature() {
        arCondicionadoGellaKaza.aumentarTemperatura();
        increaseTemperatureVentoBaumn();
    }

    public void decreaseTemperature() {
        arCondicionadoGellaKaza.diminuirTemperatura();
        decreaseTemperatureVentoBaumn();
    }

    public void defineTemperature(int temperature) {
        arCondicionadoVentoBaumn.definirTemperatura(temperature);
        adjustTemperatureGellaKaza(temperature);
    }

    private void adjustTemperatureGellaKaza(int temperature) {
        int currentTemperature = arCondicionadoGellaKaza.getTemperatura();
        
        while (temperature > currentTemperature) {
            arCondicionadoGellaKaza.aumentarTemperatura();
            currentTemperature = arCondicionadoGellaKaza.getTemperatura();
        }
        while (temperature < currentTemperature) {
            arCondicionadoGellaKaza.diminuirTemperatura();
            currentTemperature = arCondicionadoGellaKaza.getTemperatura();
        }
    }

    private void increaseTemperatureVentoBaumn() {
        int newTemperature = arCondicionadoVentoBaumn.getTemperatura() + 1;
        arCondicionadoVentoBaumn.definirTemperatura(newTemperature);
    }

    private void decreaseTemperatureVentoBaumn() {
        int newTemperature = arCondicionadoVentoBaumn.getTemperatura() - 1;
        arCondicionadoVentoBaumn.definirTemperatura(newTemperature);
    }
}