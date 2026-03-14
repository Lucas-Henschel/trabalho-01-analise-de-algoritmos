package br.furb.problema01.model;

import br.furb.problema01.enums.ShippingMethodTypeEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    @Test
    void shouldThrowExceptionWhenProductListIsEmpty() {
        Order order = new Order(ShippingMethodTypeEnum.PAC);

        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            order::calculateShippingCost
        );

        assertEquals("Lista de produtos está vazia", exception.getMessage());
    }
}
