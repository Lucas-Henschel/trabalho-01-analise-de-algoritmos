package br.furb.problema01.model;

import br.furb.problema01.interfaces.IShippingMethod;

public class ShippingMethodPickup implements IShippingMethod {
    @Override
    public double shippingCalculator(int weight) {
        return 0;
    }
}