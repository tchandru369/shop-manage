package com.merchant.management.dto;

public class MilkOrderList {

	private double orderCustProdPrice;
	private String orderCustProdType;
	private String orderCustProdCmp;
	private String orderCustProdName;
	private int orderCustProdQty;
	
	
	public MilkOrderList(double orderCustProdPrice, String orderCustProdType, String orderCustProdCmp,
			String orderCustProdName, int orderCustProdQty) {
		super();
		this.orderCustProdPrice = orderCustProdPrice;
		this.orderCustProdType = orderCustProdType;
		this.orderCustProdCmp = orderCustProdCmp;
		this.orderCustProdName = orderCustProdName;
		this.orderCustProdQty = orderCustProdQty;
	}
	
	public MilkOrderList() {
		
	}
	public double getOrderCustProdPrice() {
		return orderCustProdPrice;
	}
	public void setOrderCustProdPrice(double orderCustProdPrice) {
		this.orderCustProdPrice = orderCustProdPrice;
	}
	public String getOrderCustProdType() {
		return orderCustProdType;
	}
	public void setOrderCustProdType(String orderCustProdType) {
		this.orderCustProdType = orderCustProdType;
	}
	public String getOrderCustProdCmp() {
		return orderCustProdCmp;
	}
	public void setOrderCustProdCmp(String orderCustProdCmp) {
		this.orderCustProdCmp = orderCustProdCmp;
	}
	public String getOrderCustProdName() {
		return orderCustProdName;
	}
	public void setOrderCustProdName(String orderCustProdName) {
		this.orderCustProdName = orderCustProdName;
	}
	public int getOrderCustProdQty() {
		return orderCustProdQty;
	}
	public void setOrderCustProdQty(int orderCustProdQty) {
		this.orderCustProdQty = orderCustProdQty;
	}
	
	
}
