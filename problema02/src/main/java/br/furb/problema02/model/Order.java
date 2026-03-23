package br.furb.problema02.model;

import br.furb.problema02.order.IOrderType;

import java.math.BigDecimal;

public abstract class Order implements IOrderType {
	private final String investorName;
	private final BigDecimal orderValue;
	
	protected Order(String investorName, BigDecimal orderValue) {
		this.investorName = investorName;
		this.orderValue = orderValue;
	}
	
	public String getInvestorName() {
		return investorName;
	}
	
	public BigDecimal getOrderValue() {
		return orderValue;
	}
}
