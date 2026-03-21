package br.furb.problema02.model;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private final List<Order> orders = new ArrayList<>();

    public void add(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Ordem inválida");
        }
        orders.add(order);
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public void remove(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Ordem inválida");
        }
        orders.remove(order);
    }
}
