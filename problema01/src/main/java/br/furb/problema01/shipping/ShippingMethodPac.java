package br.furb.problema01.shipping;

public class ShippingMethodPac implements IShippingMethod {
    private static final int LIMIT_1000G = 1000;
    private static final int LIMIT_2000G = 2000;

    private static final double PRICE_UNDER_1000G = 10.0;
    private static final double PRICE_1000G_TO_2000G = 15.0;

    @Override
    public double shippingCalculator(int weightInGrams) {
        // TODO: analisar os ifs
        if (weightInGrams <= LIMIT_1000G) {
            return PRICE_UNDER_1000G;
        } else if (weightInGrams <= LIMIT_2000G) {
            return PRICE_1000G_TO_2000G;
        }

        throw new IllegalArgumentException("Não é permitido o envio de pedidos acima de 2kg");
    }
}