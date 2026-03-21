package br.furb.problema02.order;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.Order;

public class OrderTypeBuy extends Order{
	public OrderTypeBuy(String investorName, double orderValue) {
		super(investorName, orderValue);
	}
	
	public OrderTypeEnum getOrderType() {
		return OrderTypeEnum.BUY;
	}
}
