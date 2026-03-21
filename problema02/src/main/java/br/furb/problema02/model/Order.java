package br.furb.problema02.model;

import br.furb.problema02.order.IOrderType;

import java.math.BigDecimal;

public abstract class Order implements IOrderType {
	//pode ser que isso altere, depende de como vai ficar a classe do investidor acho?
	private final String investorNameString;
	private final BigDecimal orderValue;
	
	protected Order(String investorName, BigDecimal orderValue) {
		this.investorNameString = investorName;
		this.orderValue = orderValue;
	}
	
	public String getInvestorName() {
		return investorNameString;
	}
	
	public BigDecimal getOrderValue() {
		return orderValue;
	}
	
}
