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
        int current = arCondicionadoGellaKaza.getTemperatura();

        while (current != temperature) {
            if (current < temperature) {
                arCondicionadoGellaKaza.aumentarTemperatura();
                current = arCondicionadoGellaKaza.getTemperatura();
                continue;
            }

            arCondicionadoGellaKaza.diminuirTemperatura();
            current = arCondicionadoGellaKaza.getTemperatura();
        }
    }
}
