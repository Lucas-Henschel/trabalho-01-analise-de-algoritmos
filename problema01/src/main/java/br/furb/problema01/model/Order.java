package br.furb.problema01.model;

import br.furb.problema01.enums.ShippingMethodTypeEnum;
import br.furb.problema01.factories.ShippingMethodFactory;
import br.furb.problema01.shipping.IShippingMethod;

public class Order {
    private final Products products = new Products();
    private final IShippingMethod shippingMethod;

    public Order(ShippingMethodTypeEnum shippingMethodType) {
        this.shippingMethod = ShippingMethodFactory.createShippingMethod(shippingMethodType);
    }

    public double calculateShippingCost() throws IllegalArgumentException {
        if (products.isEmpty()) {
            throw new IllegalStateException("Lista de produtos está vazia");
        }

        return shippingMethod.calculateShippingCost(getProductsTotalWeightInGrams());
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    private int getProductsTotalWeightInGrams() {
        return products.totalWeightInGrams();
    }
}
