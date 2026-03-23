package br.furb.problema02.model;

import br.furb.problema02.conditionalorder.ConditionalOrder;
import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.stock.Stock;
import br.furb.problema02.observer.Observer;

import java.math.BigDecimal;

public class Investor implements Observer {
    private final String name;

    public Investor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public TradeResult orderRegister(Stock stock, BigDecimal orderValue, OrderTypeEnum orderType) {
        return stock.placeOrder(getName(), orderValue, orderType);
    }

    public void registerForStockUpdates(Stock stock) {
        stock.registerObserver(this);
    }

    @Override
    public void changedValue(Stock stock) {
        System.out.println("Investidor " + name + " notificado: " +
                stock.getName() + " mudou para " + stock.getValue());
    }
    
    public void scheduleConditionalOrder(Stock stock, ConditionalOrder order) {
        stock.scheduleConditionalOrder(order);
    }
}
