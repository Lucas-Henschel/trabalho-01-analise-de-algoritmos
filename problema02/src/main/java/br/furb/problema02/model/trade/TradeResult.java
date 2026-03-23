package br.furb.problema02.model.trade;

import br.furb.problema02.order.IOrderType;

import java.math.BigDecimal;
import java.util.Optional;

public final class TradeResult {
    private final IOrderType incomingOrder;
    private final TradeMatch tradeMatch;

    private TradeResult(IOrderType incomingOrder, TradeMatch tradeMatch) {
        this.incomingOrder = incomingOrder;
        this.tradeMatch = tradeMatch;
    }

    public static TradeResult pending(IOrderType incomingOrder) {
        return new TradeResult(incomingOrder, TradeMatch.pending());
    }

    public static TradeResult matched(IOrderType incomingOrder, IOrderType matchedOrder) {
        return new TradeResult(incomingOrder, TradeMatch.matched(matchedOrder));
    }

    public IOrderType getIncomingOrder() {
        return incomingOrder;
    }

    public Optional<IOrderType> getMatchedOrder() {
        return tradeMatch.getMatchedOrder();
    }

    public Optional<BigDecimal> getNegotiatedValue() {
        return tradeMatch.getNegotiatedValue();
    }

    public boolean hasMatch() {
        return tradeMatch.hasMatch();
    }

    public boolean isPending() {
        return tradeMatch.isPending();
    }
}
