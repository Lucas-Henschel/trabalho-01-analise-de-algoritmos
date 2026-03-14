package br.furb.problema01.factories;

import br.furb.problema01.enums.ShippingMethodTypeEnum;
import br.furb.problema01.shipping.IShippingMethod;
import br.furb.problema01.shipping.ShippingMethodPac;
import br.furb.problema01.shipping.ShippingMethodPickup;
import br.furb.problema01.shipping.ShippingMethodSedex;

public class ShippingMethodFactory {
    public static IShippingMethod createShippingMethod(ShippingMethodTypeEnum shippingMethod) throws IllegalArgumentException {
        if (shippingMethod == null) {
            throw new IllegalArgumentException("Tipo de entrega inválido");
        }

        return switch (shippingMethod) {
            case PAC -> new ShippingMethodPac();
            case SEDEX -> new ShippingMethodSedex();
            case PICKUP -> new ShippingMethodPickup();
        };
    }
}
