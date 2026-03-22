package br.furb.problema02.model;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.order.IOrderType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public int size() {
        return orders.size();
    }

    public Optional<IOrderType> findByTypeAndValue(OrderTypeEnum orderType, BigDecimal orderValue) {
        if (orderType == null || orderValue == null) {
            throw new IllegalArgumentException("Ordem inválida");
        }

        return orders.stream()
            .filter(order -> order.getOrderType() == orderType)
            .filter(order -> order.getOrderValue().compareTo(orderValue) == 0)
            .findFirst();
    }
}
