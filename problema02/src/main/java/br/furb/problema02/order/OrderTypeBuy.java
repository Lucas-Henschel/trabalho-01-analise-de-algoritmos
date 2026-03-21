package br.furb.problema02.order;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.model.Order;

import java.math.BigDecimal;

public class OrderTypeBuy extends Order{
	public OrderTypeBuy(String investorName, BigDecimal orderValue) {
		super(investorName, orderValue);
	}
	
	public OrderTypeEnum getOrderType() {
		return OrderTypeEnum.BUY;
	}
}
