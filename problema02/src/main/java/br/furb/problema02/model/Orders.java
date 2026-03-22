package br.furb.problema02.model;

import br.furb.problema02.order.IOrderType;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private final List<IOrderType> orders = new ArrayList<>();

    public void add(IOrderType order) {
        if (order == null) {
            throw new IllegalArgumentException("Ordem inválida");
        }

        orders.add(order);
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public void remove(IOrderType order) {
        if (order == null) {
            throw new IllegalArgumentException("Ordem inválida");
        }

        orders.remove(order);
    }
}
