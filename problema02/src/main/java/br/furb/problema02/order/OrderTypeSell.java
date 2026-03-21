package br.furb.problema02.order;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.Order;

public class OrderTypeSell extends Order{
	public OrderTypeSell(String investorName, double orderValue) {
		super(investorName, orderValue);
	}
	
	public OrderTypeEnum getOrderType() {
		return OrderTypeEnum.SELL;
	}
}
