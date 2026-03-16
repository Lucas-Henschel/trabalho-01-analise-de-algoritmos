package br.furb.problema01.shipping;

public class ShippingMethodSedex implements IShippingMethod {
	private static final int LIMIT_500G = 500;
    private static final int LIMIT_1000G = 1000;

    private static final double PRICE_UP_TO_500G = 12.5;
    private static final double PRICE_501G_TO_1000G = 20.0;

    private static final double BASE_PRICE = 46.5;
    private static final double PRICE_PER_100G = 1.5;
    
	@Override
	public double calculateShippingCost(int weightInGrams) {
		if (weightInGrams <= LIMIT_500G) return PRICE_UP_TO_500G;
		if (weightInGrams <= LIMIT_1000G) return PRICE_501G_TO_1000G;

		return BASE_PRICE + calculateAdditionalCostPer100g(weightInGrams);
	};

	private static double calculateAdditionalCostPer100g(int weightInGrams){
        return (PRICE_PER_100G * (calculateWeightExceeding1000g(weightInGrams) / 100));
    }
 
    private static int calculateWeightExceeding1000g(int weightInGrams){
        return weightInGrams - 1000;
    }
}
