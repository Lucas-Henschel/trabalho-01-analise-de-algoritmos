package br.furb.problema01.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsTest {
    @Test
    void shouldBeEmptyWhenCreated() {
        Products products = new Products();

        assertTrue(products.isEmpty());
    }

    @Test
    void shouldAddProductSuccessfully() {
        Products products = new Products();
        Product product = new Product("Livro", new BigDecimal("10.0"), 500);

        products.add(product);

        assertFalse(products.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenAddingNullProduct() {
        Products products = new Products();

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> products.add(null)
        );

        assertEquals("Produto inválido", exception.getMessage());
    }

    @Test
    void shouldCalculateTotalWeightWithOneProduct() {
        Products products = new Products();
        products.add(new Product("Livro", new BigDecimal("10.0"), 500));

        int totalWeight = products.totalWeight();
        assertEquals(500, totalWeight);
    }

    @Test
    void shouldCalculateTotalWeightWithMultipleProducts() {
        Products products = new Products();
        products.add(new Product("Livro", new BigDecimal("10.0"), 400));
        products.add(new Product("Caderno", new BigDecimal("20.0"), 600));

        int totalWeight = products.totalWeight();
        assertEquals(1000, totalWeight);
    }
}
