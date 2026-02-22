package com.merchant.management.dto;

public class CustOverAllPymtStatusRes {
	
	private String custName;
	private String custEmail;
	private String ownerEmail;
	private String billDate;
	private String paymentDate;
	private String pymtOverAllStatus;
	private double pymtAmountBal;
	private double totalPymtAmt;
	private double custPaidAmt;
	private String fullPaymentFlg;
	
	public CustOverAllPymtStatusRes() {
		
	}

	public CustOverAllPymtStatusRes(String custName, String custEmail, String ownerEmail, String billDate,
			String paymentDate, String pymtOverAllStatus, double pymtAmountBal, double totalPymtAmt, double custPaidAmt,
			String fullPaymentFlg) {
		super();
		this.custName = custName;
		this.custEmail = custEmail;
		this.ownerEmail = ownerEmail;
		this.billDate = billDate;
		this.paymentDate = paymentDate;
		this.pymtOverAllStatus = pymtOverAllStatus;
		this.pymtAmountBal = pymtAmountBal;
		this.totalPymtAmt = totalPymtAmt;
		this.custPaidAmt = custPaidAmt;
		this.fullPaymentFlg = fullPaymentFlg;
	}




	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPymtOverAllStatus() {
		return pymtOverAllStatus;
	}
	public void setPymtOverAllStatus(String pymtOverAllStatus) {
		this.pymtOverAllStatus = pymtOverAllStatus;
	}
	public double getPymtAmountBal() {
		return pymtAmountBal;
	}
	public void setPymtAmountBal(double pymtAmountBal) {
		this.pymtAmountBal = pymtAmountBal;
	}
	public String getFullPaymentFlg() {
		return fullPaymentFlg;
	}
	public void setFullPaymentFlg(String fullPaymentFlg) {
		this.fullPaymentFlg = fullPaymentFlg;
	}


	public double getTotalPymtAmt() {
		return totalPymtAmt;
	}


	public void setTotalPymtAmt(double totalPymtAmt) {
		this.totalPymtAmt = totalPymtAmt;
	}


	public double getCustPaidAmt() {
		return custPaidAmt;
	}


	public void setCustPaidAmt(double custPaidAmt) {
		this.custPaidAmt = custPaidAmt;
	}
	
	

}
