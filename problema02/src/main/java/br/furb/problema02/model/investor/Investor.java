package br.furb.problema02.model.investor;

import br.furb.problema02.conditionalOrder.ConditionalOrder;
import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.stock.Stock;
import br.furb.problema02.model.trade.TradeResult;
import br.furb.problema02.observer.Observer;

import java.math.BigDecimal;

public class Investor implements Observer {
    private final InvestorInfo investorInfo;
    private final InvestorRuntime investorRuntime;

    public Investor(String name) {
        investorInfo = new InvestorInfo(name);
        investorRuntime = new InvestorRuntime(investorInfo);
    }

    public String getName() {
        return investorInfo.getName();
    }

    public TradeResult orderRegister(Stock stock, BigDecimal orderValue, OrderTypeEnum orderType) {
        return investorRuntime.orderRegister(stock, orderValue, orderType);
    }

    public void registerForStockUpdates(Stock stock) {
        investorRuntime.registerForStockUpdates(this, stock);
    }

    @Override
    public void changedValue(Stock stock) {
        investorRuntime.changedValue(stock);
    }

    public void scheduleConditionalOrder(Stock stock, ConditionalOrder order) {
        investorRuntime.scheduleConditionalOrder(stock, order);
    }
}
