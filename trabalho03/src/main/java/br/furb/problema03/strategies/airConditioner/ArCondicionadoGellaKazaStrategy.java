package br.furb.problema03.strategies.airConditioner;

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza;

public class ArCondicionadoGellaKazaStrategy implements AirConditionerStrategy {
    private final ArCondicionadoGellaKaza arCondicionadoGellaKaza;

    public ArCondicionadoGellaKazaStrategy(ArCondicionadoGellaKaza arCondicionadoGellaKaza) {
        this.arCondicionadoGellaKaza = arCondicionadoGellaKaza;
    }

    @Override
    public void turnOn() {
        arCondicionadoGellaKaza.ativar();
    }

    @Override
    public void turnOff() {
        arCondicionadoGellaKaza.desativar();
    }

    @Override
    public void increaseTemperature() {
        arCondicionadoGellaKaza.aumentarTemperatura();
    }

    @Override
    public void decreaseTemperature() {
        arCondicionadoGellaKaza.diminuirTemperatura();
    }

    @Override
    public void defineTemperature(int temperature) {
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
}
