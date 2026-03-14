package br.furb.problema01.factories;

import br.furb.problema01.enums.ShippingMethodTypeEnum;
import br.furb.problema01.shipping.IShippingMethod;
import br.furb.problema01.shipping.ShippingMethodPac;
import br.furb.problema01.shipping.ShippingMethodSedex;
import br.furb.problema01.shipping.ShippingMethodPickup;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShippingMethodFactoryTest {
    @Test
    void shouldReturnPacShipping() {
        IShippingMethod shippingMethod = ShippingMethodFactory.createShippingMethod(ShippingMethodTypeEnum.PAC);

        assertInstanceOf(ShippingMethodPac.class, shippingMethod);
    }

    @Test
    void shouldReturnSedexShipping() {
        IShippingMethod shippingMethod = ShippingMethodFactory.createShippingMethod(ShippingMethodTypeEnum.SEDEX);

        assertInstanceOf(ShippingMethodSedex.class, shippingMethod);
    }

    @Test
    void shouldReturnPickupShipping() {
        IShippingMethod shippingMethod = ShippingMethodFactory.createShippingMethod(ShippingMethodTypeEnum.PICKUP);

        assertInstanceOf(ShippingMethodPickup.class, shippingMethod);
    }

    @Test
    void shouldThrowExceptionWhenShippingMethodIsNull() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> ShippingMethodFactory.createShippingMethod(null)
        );

        assertEquals("Tipo de entrega inválido", exception.getMessage());
    }
}
