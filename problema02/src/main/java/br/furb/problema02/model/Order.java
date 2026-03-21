package br.furb.problema02.model;

import br.furb.problema02.order.IOrderType;

public abstract class Order implements IOrderType{
	//pode ser que isso altere, depende de como vai ficar a classe do investidor acho?
	private final String investorNameString;
	private final double orderValue;
	
	protected Order(String investorName, double orderValue) {
		this.investorNameString = investorName;
		this.orderValue = orderValue;
	}
	
	public String getInvestorName() {
		return investorNameString;
	}
	
	public double getOrderValue() {
		return orderValue;
	}
	
}
