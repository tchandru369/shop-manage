package com.merchant.management.dto;

public class UserCustLastTransaction {
	private String orderBillPayDate;
	private String orderPlacedDate;
	private double orderProdTotalAmt;
	private String orderReqStatus;
	public String getOrderBillPayDate() {
		return orderBillPayDate;
	}
	public void setOrderBillPayDate(String orderBillPayDate) {
		this.orderBillPayDate = orderBillPayDate;
	}
	public String getOrderPlacedDate() {
		return orderPlacedDate;
	}
	public void setOrderPlacedDate(String orderPlacedDate) {
		this.orderPlacedDate = orderPlacedDate;
	}
	public double getOrderProdTotalAmt() {
		return orderProdTotalAmt;
	}
	public void setOrderProdTotalAmt(double orderProdTotalAmt) {
		this.orderProdTotalAmt = orderProdTotalAmt;
	}
	public String getOrderReqStatus() {
		return orderReqStatus;
	}
	public void setOrderReqStatus(String orderReqStatus) {
		this.orderReqStatus = orderReqStatus;
	}
	
	
}
