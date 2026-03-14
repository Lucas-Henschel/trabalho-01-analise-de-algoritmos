package br.furb.problema01.factories;

import br.furb.problema01.interfaces.IShippingMethod;

public class ShippingMethodPickup implements IShippingMethod {
    @Override
    public double shippingCalculator(int weightInGrams) {
        return 0;
    }
}