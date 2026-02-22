package com.merchant.management.dto;

public class CustProdPriceCount {
	
	private String monthValue;
	private double monthlyPrice;
	private double monthlyProductQty;
	private int totalOrders;
	
	
	public CustProdPriceCount(String monthValue, double monthlyPrice, double monthlyProductQty,int totalOrders) {
		super();
		this.monthValue = monthValue;
		this.monthlyPrice = monthlyPrice;
		this.monthlyProductQty = monthlyProductQty;
		this.totalOrders = totalOrders;
	}
	
	
	public int getTotalOrders() {
		return totalOrders;
	}


	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}


	public String getMonthValue() {
		return monthValue;
	}
	public void setMonthValue(String monthValue) {
		this.monthValue = monthValue;
	}
	public double getMonthlyPrice() {
		return monthlyPrice;
	}
	public void setMonthlyPrice(double monthlyPrice) {
		this.monthlyPrice = monthlyPrice;
	}
	public double getMonthlyProductQty() {
		return monthlyProductQty;
	}
	public void setMonthlyProductQty(double monthlyProductQty) {
		this.monthlyProductQty = monthlyProductQty;
	}
	
	
	

}
