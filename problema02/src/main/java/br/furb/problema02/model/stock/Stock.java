package br.furb.problema02.model.stock;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.factories.OrderTypeFactory;
import br.furb.problema02.model.Orders;
import br.furb.problema02.observer.Observer;
import br.furb.problema02.observer.Subject;
import br.furb.problema02.order.IOrderType;

import java.math.BigDecimal;
import java.util.Optional;

public class Stock implements Subject {
    private final StockInfo stockInfo;
    private final StockState stockState = new StockState();

    public String getName() {
        return stockInfo.getName();
    }

    public BigDecimal getValue() {
        return stockInfo.getValue();
    }

    public Orders getOrders() {
        return stockState.getOrders();
    }

    public Stock(String name, BigDecimal value) {
        this.stockInfo = new StockInfo(name, value);
    }

    public void placeOrder(String investorName, BigDecimal orderValue, OrderTypeEnum orderType) {
        IOrderType newOrder = OrderTypeFactory.createOrder(investorName, orderValue, orderType);
        Optional<IOrderType> matchedOrder = getOrders().findByTypeAndValue(orderType.opposite(), orderValue);

        if (matchedOrder.isPresent()) {
            getOrders().remove(matchedOrder.get());
            stockInfo.setValue(matchedOrder.get().getOrderValue());

            return;
        }

        getOrders().add(newOrder);
    }

    @Override
    public void registerObserver(Observer observer) {
        stockState.getObserverStocks().register(observer);
    }
}
