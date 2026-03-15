package br.furb.problema01.model;

import java.util.ArrayList;
import java.util.List;

public class Products {
    private final List<Product> products = new ArrayList<>();

    public void add(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Produto inválido");
        }

        products.add(product);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public int totalWeight() {
        // TOOD: mudar o product.getWeight().inGrams() para um metodo
        return products.stream()
            .mapToInt(product -> product.getWeight().inGrams())
            .sum();
    }
}
