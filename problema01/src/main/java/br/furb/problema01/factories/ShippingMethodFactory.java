package br.furb.problema01.factories;

import br.furb.problema01.enums.ShippingMethodType;

public class ShippingMethodFactory {
    public static void criaFormatoEntrega(ShippingMethodType shippingMethod) {
        switch (shippingMethod) {
            case PAC:
                break;
            case SEDEX:
                break;
            case PICKUP:
                break;
            default:
                break;
        }
    }
}
