package br.furb.problema02.service;

import br.furb.problema02.conditionalOrder.ConditionalOrder;
import br.furb.problema02.model.order.Orders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StockState {
    private final Orders orders = new Orders();
    private final List<ConditionalOrder> conditionalOrders = new ArrayList<>();

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

    public List<ConditionalOrder> findExecutableConditionalOrders(BigDecimal stockValue) {
        List<ConditionalOrder> executableOrders = new ArrayList<>();
        
        for (ConditionalOrder conditionalOrder : conditionalOrders) {
            if (conditionalOrder.shouldExecute(stockValue)) {
                executableOrders.add(conditionalOrder);
            }
        }

        return executableOrders;
    }

    public boolean removeConditionalOrder(ConditionalOrder conditionalOrder) {
        return conditionalOrders.remove(conditionalOrder);
    }
}
