package br.furb.problema02.model;

import br.furb.problema02.order.IOrderType;

import java.math.BigDecimal;
import java.util.Optional;

public final class TradeResult {
    private final IOrderType incomingOrder;
    private final IOrderType matchedOrder;
    private final BigDecimal negotiatedValue;

    private TradeResult(IOrderType incomingOrder, IOrderType matchedOrder, BigDecimal negotiatedValue) {
        this.incomingOrder = incomingOrder;
        this.matchedOrder = matchedOrder;
        this.negotiatedValue = negotiatedValue;
    }

    public static TradeResult pending(IOrderType incomingOrder) {
        return new TradeResult(incomingOrder, null, null);
    }

    public static TradeResult matched(IOrderType incomingOrder, IOrderType matchedOrder) {
        return new TradeResult(incomingOrder, matchedOrder, matchedOrder.getOrderValue());
    }

    public IOrderType getIncomingOrder() {
        return incomingOrder;
    }

    public Optional<IOrderType> getMatchedOrder() {
        return Optional.ofNullable(matchedOrder);
    }

    public Optional<BigDecimal> getNegotiatedValue() {
        return Optional.ofNullable(negotiatedValue);
    }

    public boolean hasMatch() {
        return matchedOrder != null;
    }

    public boolean isPending() {
        return !hasMatch();
    }
}
