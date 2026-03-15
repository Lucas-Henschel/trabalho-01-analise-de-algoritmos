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
        assertEquals(10.0, shippingMethodPac.shippingCalculator(600));
    }

    @Test
    void shouldReturnPriceBetween1000gAnd2000g() {
        assertEquals(15.0, shippingMethodPac.shippingCalculator(1800));
    }

    @Test
    void shouldReturnPriceWhenWeightIsExactly1000g() {
        assertEquals(10.0, shippingMethodPac.shippingCalculator(1000));
    }

    @Test
    void shouldReturnPriceWhenWeightIsExactly2000g() {
        assertEquals(15.0, shippingMethodPac.shippingCalculator(2000));
    }

    @Test
    void shouldThrowExceptionWhenWeightIsAbove2000g() {
        assertThrows(IllegalArgumentException.class, () -> {
            shippingMethodPac.shippingCalculator(2500);
        });
    }

    @Test
    void shouldThrowExceptionWhenWeightIsFarAboveLimit() {
        assertThrows(IllegalArgumentException.class, () -> {
            shippingMethodPac.shippingCalculator(5000);
        });
    }
}
