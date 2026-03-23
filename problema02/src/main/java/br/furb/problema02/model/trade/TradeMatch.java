package br.furb.problema02.model.trade;

import br.furb.problema02.order.IOrderType;

import java.math.BigDecimal;
import java.util.Optional;

final class TradeMatch {
    private final Optional<IOrderType> matchedOrder;

    private TradeMatch(Optional<IOrderType> matchedOrder) {
        this.matchedOrder = matchedOrder;
    }

    static TradeMatch pending() {
        return new TradeMatch(Optional.empty());
    }

    static TradeMatch matched(IOrderType matchedOrder) {
        return new TradeMatch(Optional.of(matchedOrder));
    }

    Optional<IOrderType> getMatchedOrder() {
        return matchedOrder;
    }

    Optional<BigDecimal> getNegotiatedValue() {
        return matchedOrder.map(IOrderType::getOrderValue);
    }

    boolean hasMatch() {
        return matchedOrder.isPresent();
    }

    boolean isPending() {
        return matchedOrder.isEmpty();
    }
}
