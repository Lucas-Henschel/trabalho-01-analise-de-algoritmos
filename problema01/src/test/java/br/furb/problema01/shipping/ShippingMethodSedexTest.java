package br.furb.problema01.shipping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.furb.problema01.enums.ShippingMethodTypeEnum;
import br.furb.problema01.model.Order;
import br.furb.problema01.model.Product;

public class ShippingMethodSedexTest {
    @Test
    void shouldReturnPriceUnder500Grams() {
        Order order = new Order(ShippingMethodTypeEnum.SEDEX);
        order.addProduct(new Product("Livro infantil", new BigDecimal("10.0"), 200));

        double shippingCost = order.calculateShippingCost();

        assertEquals(12.5, shippingCost);
    }

    @Test
    void shouldReturnPriceAt500GramsBoundary() {
        Order order = new Order(ShippingMethodTypeEnum.SEDEX);
        order.addProduct(new Product("Livro volume 1", new BigDecimal("10.0"), 500));

        double shippingCost = order.calculateShippingCost();

        assertEquals(12.5, shippingCost);
    }

    @Test
    void shouldReturnPrice501GramsTo1000Grams() {
        Order order = new Order(ShippingMethodTypeEnum.SEDEX);
        order.addProduct(new Product("Livro de medicina", new BigDecimal("10.0"), 600));

        double shippingCost = order.calculateShippingCost();

        assertEquals(20.0, shippingCost);
    }

    @Test
    void shouldReturnPriceAt1000GramsBoundary() {
        Order order = new Order(ShippingMethodTypeEnum.SEDEX);
        order.addProduct(new Product("Livro volume 2", new BigDecimal("10.0"), 1000));

        double shippingCost = order.calculateShippingCost();

        assertEquals(20.0, shippingCost);
    }

    @Test
    void shouldReturnPriceAbove1000Grams() {
        Order order = new Order(ShippingMethodTypeEnum.SEDEX);
        order.addProduct(new Product("Livro de direito", new BigDecimal("10.0"), 1150));

        double shippingCost = order.calculateShippingCost();

        assertEquals(48.0, shippingCost);
    }
}