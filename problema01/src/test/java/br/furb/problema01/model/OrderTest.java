package br.furb.problema01.model;

import br.furb.problema01.enums.ShippingMethodTypeEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    @Test
    void shouldThrowExceptionWhenProductListIsEmpty() {
        OrderModel order = new OrderModel(ShippingMethodTypeEnum.PAC);

        Exception exception = assertThrows(Exception.class, () -> {
            order.getShippingCost();
        });

        assertEquals("Lista de produtos está vazia", exception.getMessage());
    }
}
