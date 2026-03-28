package br.furb.problema03.strategies.airConditioner;

import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn;

public class ArCondicionadoVentoBaumnStrategy implements AirConditionerStrategy {
    private final ArCondicionadoVentoBaumn arCondicionadoVentoBaumn;

    public ArCondicionadoVentoBaumnStrategy(ArCondicionadoVentoBaumn arCondicionadoVentoBaumn) {
        this.arCondicionadoVentoBaumn = arCondicionadoVentoBaumn;
    }

    @Override
    public void turnOn() {
        arCondicionadoVentoBaumn.ligar();
    }

    @Override
    public void turnOff() {
        arCondicionadoVentoBaumn.desligar();
    }

    @Override
    public void increaseTemperature() {
        arCondicionadoVentoBaumn.definirTemperatura(arCondicionadoVentoBaumn.getTemperatura() + 1);
    }

    @Override
    public void decreaseTemperature() {
        arCondicionadoVentoBaumn.definirTemperatura(arCondicionadoVentoBaumn.getTemperatura() - 1);
    }

    @Override
    public void defineTemperature(int temperature) {
        arCondicionadoVentoBaumn.definirTemperatura(temperature);
    }
}
