package br.furb.problema02.order;

import br.furb.problema02.enums.OrderTypeEnum;

import java.math.BigDecimal;

public interface IOrderType {
	String getInvestorName();
	BigDecimal getOrderValue();
	OrderTypeEnum getOrderType();
}
