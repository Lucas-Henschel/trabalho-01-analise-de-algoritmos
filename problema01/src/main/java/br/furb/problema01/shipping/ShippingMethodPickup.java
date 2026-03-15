package br.furb.problema01.shipping;

public class ShippingMethodPickup implements IShippingMethod {
    @Override
    public double calculateShippingCost(int weightInGrams) {
        return 0;
    }
}