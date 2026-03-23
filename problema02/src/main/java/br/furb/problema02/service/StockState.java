package br.furb.problema02.service;

import java.util.ArrayList;
import java.util.List;

import br.furb.problema02.conditionalOrder.ConditionalOrder;
import br.furb.problema02.model.order.Orders;

public class StockState {
    private final Orders orders = new Orders();
    private final List<ConditionalOrder> conditionalOrders = new ArrayList<>();

    public StockState() {
    }

    public Orders getOrders() {
        return orders;
    }

    public void addConditionalOrder(ConditionalOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Ordem inválida");
        }

        conditionalOrders.add(order);
    }

    public List<ConditionalOrder> getConditionalOrders() {
        return conditionalOrders;
    }
}
