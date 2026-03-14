package br.furb.problema01.model;

import br.furb.problema01.enums.ShippingMethodTypeEnum;
import br.furb.problema01.factories.ShippingMethodFactory;
import br.furb.problema01.interfaces.IShippingMethod;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    private List<ProductModel> productModels = new ArrayList<>();
    private ShippingMethodTypeEnum shippingMethodType;

    public OrderModel(ShippingMethodTypeEnum shippingMethodType) {
        this.shippingMethodType = shippingMethodType;
    }

    private int getTotalWeight() {
        return productModels.stream()
            .mapToInt(ProductModel::getWeight)
            .sum();
    }

    public double getShippingCost() throws IllegalArgumentException {
        if (productModels.isEmpty()) {
            throw new IllegalStateException("Lista de produtos está vazia");
        }

        IShippingMethod shippingMethod = ShippingMethodFactory.createShippingMethod(getShippingMethodType());
        return shippingMethod.shippingCalculator(getTotalWeight());
    }

    public void addProduct(ProductModel productModel) {
        productModels.add(productModel);
    }

    public ShippingMethodTypeEnum getShippingMethodType() {
        return shippingMethodType;
    }
}
