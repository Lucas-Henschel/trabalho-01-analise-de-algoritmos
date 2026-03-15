package br.furb.problema01.shipping;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class ShippingMethodPickupTest {

    @InjectMocks
    private ShippingMethodPickup shippingMethodPickup = new ShippingMethodPickup();

    @Test
    void shouldReturnZeroShippingCostForPickupWhenWeightIsLarge() {
        assertEquals(0, shippingMethodPickup.calculateShippingCost(5000));
    }

    @Test
    void shouldReturnZeroShippingCostForPickupWhenWeightIsSmall() {
        assertEquals(0, shippingMethodPickup.calculateShippingCost(50));
    }

    @Test
    void shouldReturnZeroShippingCostForPickupWhenWeightIsVeryLarge() {
        assertEquals(0, shippingMethodPickup.calculateShippingCost(900000));
    }
}