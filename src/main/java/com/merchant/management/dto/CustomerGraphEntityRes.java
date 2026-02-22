package com.merchant.management.dto;

public class CustomerGraphEntityRes {
	
	private String custFnlDate;
	private double custTotalAmt;
	private double custBlnAmt;
	private long custProdQty;
	
	
	
	public CustomerGraphEntityRes(String custFnlDate, double custTotalAmt, double custBlnAmt, long custProdQty) {
		super();
		this.custFnlDate = custFnlDate;
		this.custTotalAmt = custTotalAmt;
		this.custBlnAmt = custBlnAmt;
		this.custProdQty = custProdQty;
	}
	
	public String getCustFnlDate() {
		return custFnlDate;
	}
	public void setCustFnlDate(String custFnlDate) {
		this.custFnlDate = custFnlDate;
	}
	public double getCustTotalAmt() {
		return custTotalAmt;
	}
	public void setCustTotalAmt(double custTotalAmt) {
		this.custTotalAmt = custTotalAmt;
	}
	public double getCustBlnAmt() {
		return custBlnAmt;
	}
	public void setCustBlnAmt(double custBlnAmt) {
		this.custBlnAmt = custBlnAmt;
	}
	public long getCustProdQty() {
		return custProdQty;
	}
	public void setCustProdQty(long custProdQty) {
		this.custProdQty = custProdQty;
	}
	
	

}
