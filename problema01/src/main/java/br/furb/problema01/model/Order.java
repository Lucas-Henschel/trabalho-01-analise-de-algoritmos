package br.furb.problema01.model;

import br.furb.problema01.enums.ShippingMethodType;
import br.furb.problema01.factories.ShippingMethodFactory;
import br.furb.problema01.interfaces.IShippingMethod;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> products = new ArrayList<>();
    private ShippingMethodType shippingMethodType;

    public Order(ShippingMethodType shippingMethodType) {
        this.shippingMethodType = shippingMethodType;
    }

    private int getTotalWeight() {
        return products.stream()
            .mapToInt(Product::getWeight)
            .sum();
    }

    public double getShippingCost() throws IllegalArgumentException {
        if (products.isEmpty()) {
            throw new IllegalStateException("Lista de produtos está vazia");
        }

        IShippingMethod shippingMethod = ShippingMethodFactory.createShippingMethod(getShippingMethodType());
        return shippingMethod.shippingCalculator(getTotalWeight());
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public ShippingMethodType getShippingMethodType() {
        return shippingMethodType;
    }
}
