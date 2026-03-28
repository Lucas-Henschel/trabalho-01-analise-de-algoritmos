package br.furb.problema03.strategies.airConditioner;

public interface AirConditionerStrategy {
    void turnOn();
    void turnOff();
    void increaseTemperature();
    void decreaseTemperature();
    void defineTemperature(int temperature);
}
