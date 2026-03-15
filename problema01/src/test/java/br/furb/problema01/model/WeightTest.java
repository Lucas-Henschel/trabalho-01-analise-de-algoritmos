package br.furb.problema01.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeightTest {
    @Test
    void shouldReturnWeightInGrams() {
        Weight weight = new Weight(400);

        assertEquals(400, weight.inGrams());
    }

    @Test
    void shouldThrowExceptionWhenWeightIsNegative() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Weight(-10)
        );

        assertEquals("Peso inválido", exception.getMessage());
    }
}
