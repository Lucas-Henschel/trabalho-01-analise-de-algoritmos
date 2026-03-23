package br.furb.problema02.service.trade;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.stock.Stock;
import br.furb.problema02.model.trade.TradeResult;
import br.furb.problema02.order.IOrderType;

@FunctionalInterface
public interface TradeOrderProcessor {
    TradeResult process(Stock stock, IOrderType order, OrderTypeEnum orderType);
}
