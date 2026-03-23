package br.furb.problema02.model.investor;

import br.furb.problema02.conditionalOrder.ConditionalOrder;
import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.stock.Stock;
import br.furb.problema02.model.trade.TradeResult;
import br.furb.problema02.observer.Observer;

import java.math.BigDecimal;

class InvestorRuntime {
    private final InvestorInfo investorInfo;

    public InvestorRuntime(InvestorInfo investorInfo) {
        this.investorInfo = investorInfo;
    }

    public TradeResult orderRegister(Stock stock, BigDecimal orderValue, OrderTypeEnum orderType) {
        validateStock(stock);
        return stock.placeOrder(investorInfo.getName(), orderValue, orderType);
    }

    public void registerForStockUpdates(Observer observer, Stock stock) {
        validateStock(stock);
        stock.registerObserver(observer);
    }

    public void changedValue(Stock stock) {
        System.out.println("Investidor " + investorInfo.getName() + " notificado: " + stock.getName() + " mudou para " + stock.getValue());
    }

    public void scheduleConditionalOrder(Stock stock, ConditionalOrder order) {
        validateStock(stock);
        validateOrder(order);
        stock.scheduleConditionalOrder(order);
    }

    private void validateStock(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException("Ação inválida");
        }
    }

    private void validateOrder(ConditionalOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Ordem inválida");
        }
    }
}
