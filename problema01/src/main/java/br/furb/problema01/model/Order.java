package br.furb.problema01.model;

import br.furb.problema01.enums.ShippingMethodTypeEnum;
import br.furb.problema01.factories.ShippingMethodFactory;
import br.furb.problema01.shipping.IShippingMethod;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> products = new ArrayList<>();
    private IShippingMethod shippingMethod;

    public Order(ShippingMethodTypeEnum shippingMethodType) {
        this.shippingMethod = ShippingMethodFactory.createShippingMethod(shippingMethodType);
    }

    private int getTotalWeight() {
        return products.stream()
            .mapToInt(Product::getWeight)
            .sum();
    }

    public double calculateShippingCost() throws IllegalArgumentException {
        if (products.isEmpty()) {
            throw new IllegalStateException("Lista de produtos está vazia");
        }

        return shippingMethod.shippingCalculator(getTotalWeight());
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Produto inválido");
        }

        products.add(product);
    }
}
