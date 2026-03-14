package br.furb.problema01.factories;

import br.furb.problema01.enums.ShippingMethodType;
import br.furb.problema01.interfaces.IShippingMethod;

public class ShippingMethodFactory {
    public static IShippingMethod createShippingMethod(ShippingMethodType shippingMethod) throws IllegalArgumentException {
        switch (shippingMethod) {
            case PAC:
                break;
            case SEDEX:
                break;
            case PICKUP:
                break;
        }

        throw new IllegalArgumentException("Tipo de entrega inválido");
    }
}
