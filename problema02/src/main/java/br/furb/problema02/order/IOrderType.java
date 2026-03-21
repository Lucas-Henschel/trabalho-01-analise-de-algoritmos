package br.furb.problema02.order;

import br.furb.problema02.enums.OrderTypeEnum;

public interface IOrderType {
	String getInvestorName();
	double getOrderValue();
	OrderTypeEnum getOrderType();
}
