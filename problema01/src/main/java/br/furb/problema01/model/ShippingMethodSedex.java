package br.furb.problema01.model;

import br.furb.problema01.interfaces.IShippingMethod;

public class ShippingMethodSedex implements IShippingMethod {
	private static final int LIMIT_500G = 500;
    private static final int LIMIT_1000G = 1000;

    private static final double PRICE_UNDER_500 = 12.5;
    private static final double PRICE_UNDER_1000 = 20.0;

    private static final double BASE_PRICE = 46.5;
    private static final double PRICE_PER_100G = 1.5;
    
	@Override
	public double shippingCalculator(int weight) {
		if(weight < LIMIT_500G) {
			return PRICE_UNDER_500;
		}
		
		else if (weight < LIMIT_1000G) {
			return PRICE_UNDER_1000;
		}
		
		return BASE_PRICE + (PRICE_PER_100G * (weight / 100));
	};
}
