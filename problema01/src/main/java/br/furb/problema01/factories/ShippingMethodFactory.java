package br.furb.problema01.factories;

import br.furb.problema01.enums.ShippingMethodTypeEnum;
import br.furb.problema01.interfaces.IShippingMethod;

public class ShippingMethodFactory {
    public static IShippingMethod createShippingMethod(ShippingMethodTypeEnum shippingMethod) throws IllegalArgumentException {
        return switch (shippingMethod) {
            case PAC -> new ShippingMethodPac();
            case SEDEX -> new ShippingMethodSedex();
            case PICKUP -> new ShippingMethodPickup();
            default -> throw new IllegalArgumentException("Tipo de entrega inválido");
        };
    }
}
