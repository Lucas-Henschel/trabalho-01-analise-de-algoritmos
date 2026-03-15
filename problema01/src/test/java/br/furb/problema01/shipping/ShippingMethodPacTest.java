package br.furb.problema01.shipping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class ShippingMethodPacTest {

    @InjectMocks
    private ShippingMethodPac shippingMethodPac = new ShippingMethodPac();

    @Test
    void shouldReturnPriceUnder1000g() {
        assertEquals(10.0, shippingMethodPac.calculateShippingCost(600));
    }

    @Test
    void shouldReturnPriceBetween1000gAnd2000g() {
        assertEquals(15.0, shippingMethodPac.calculateShippingCost(1800));
    }

    @Test
    void shouldReturnPriceWhenWeightIsExactly1000g() {
        assertEquals(10.0, shippingMethodPac.calculateShippingCost(1000));
    }

    @Test
    void shouldReturnPriceWhenWeightIsExactly2000g() {
        assertEquals(15.0, shippingMethodPac.calculateShippingCost(2000));
    }

    @Test
    void shouldThrowExceptionWhenWeightIsAbove2000g() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> shippingMethodPac.calculateShippingCost(2500)
        );

        assertEquals("Não é permitido o envio de pedidos acima de 2kg", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenWeightIsFarAboveLimit() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> shippingMethodPac.calculateShippingCost(5000)
        );

        assertEquals("Não é permitido o envio de pedidos acima de 2kg", exception.getMessage());
    }
}