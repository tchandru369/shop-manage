package com.merchant.management.dto;

public class UserCustBalDto {
	
	private double custBalActAmt;
	private double custBalAmt;
	private String custBalDate;
	private double custBalPaidAmt;
	private String custBalOrderStatus;
	
	public double getCustBalActAmt() {
		return custBalActAmt;
	}
	public void setCustBalActAmt(double custBalActAmt) {
		this.custBalActAmt = custBalActAmt;
	}
	public double getCustBalAmt() {
		return custBalAmt;
	}
	public void setCustBalAmt(double custBalAmt) {
		this.custBalAmt = custBalAmt;
	}
	public String getCustBalDate() {
		return custBalDate;
	}
	public void setCustBalDate(String custBalDate) {
		this.custBalDate = custBalDate;
	}
	public double getCustBalPaidAmt() {
		return custBalPaidAmt;
	}
	public void setCustBalPaidAmt(double custBalPaidAmt) {
		this.custBalPaidAmt = custBalPaidAmt;
	}
	public String getCustBalOrderStatus() {
		return custBalOrderStatus;
	}
	public void setCustBalOrderStatus(String custBalOrderStatus) {
		this.custBalOrderStatus = custBalOrderStatus;
	}

	
}
