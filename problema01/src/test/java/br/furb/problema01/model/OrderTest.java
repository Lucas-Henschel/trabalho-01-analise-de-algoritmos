package br.furb.problema01.model;

import br.furb.problema01.enums.ShippingMethodTypeEnum;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    @Test
    void shouldCalculatePacShippingCost() {
        Order order = new Order(ShippingMethodTypeEnum.PAC);
        order.addProduct(new Product("Livro", new BigDecimal("10.0"), 800));

        double shippingCost = order.calculateShippingCost();

        assertEquals(10.0, shippingCost);
    }

    @Test
    void shouldCalculateSedexShippingCost() {
        Order order = new Order(ShippingMethodTypeEnum.SEDEX);
        order.addProduct(new Product("Caderno", new BigDecimal("10.0"), 400));

        double shippingCost = order.calculateShippingCost();

        assertEquals(12.5, shippingCost);
    }

    @Test
    void shouldCalculatePickupShippingCost() {
        Order order = new Order(ShippingMethodTypeEnum.PICKUP);
        order.addProduct(new Product("TV", new BigDecimal("10.0"), 1500));

        double shippingCost = order.calculateShippingCost();

        assertEquals(0, shippingCost);
    }

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
