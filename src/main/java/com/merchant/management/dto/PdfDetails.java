package com.merchant.management.dto;

public class PdfDetails {
	
	private String ownerName;
	private String ownerAddress;
	private String ownerEmail;
	private String ownerPhNo;
	private String customerName;
	private String customerAddrs;
	private String customerEmail;
	private String customerPhNo;
	private String invoiceNumber;
	private double custBlnAmount;
	private double finalAmtPaid;
	private String custPymtDesc;
	
	public PdfDetails() {
		
	}
	
	public PdfDetails(String ownerName, String ownerAddress, String ownerEmail, String ownerPhNo, String customerName,
			String customerAddrs, String customerEmail, String customerPhNo, String invoiceNumber, double custBlnAmount,
			double finalAmtPaid, String custPymtDesc) {
		super();
		this.ownerName = ownerName;
		this.ownerAddress = ownerAddress;
		this.ownerEmail = ownerEmail;
		this.ownerPhNo = ownerPhNo;
		this.customerName = customerName;
		this.customerAddrs = customerAddrs;
		this.customerEmail = customerEmail;
		this.customerPhNo = customerPhNo;
		this.invoiceNumber = invoiceNumber;
		this.custBlnAmount = custBlnAmount;
		this.finalAmtPaid = finalAmtPaid;
		this.custPymtDesc = custPymtDesc;
	}

	public double getFinalAmtPaid() {
		return finalAmtPaid;
	}

	public void setFinalAmtPaid(double finalAmtPaid) {
		this.finalAmtPaid = finalAmtPaid;
	}

	public String getCustPymtDesc() {
		return custPymtDesc;
	}

	public void setCustPymtDesc(String custPymtDesc) {
		this.custPymtDesc = custPymtDesc;
	}

	public double getCustBlnAmount() {
		return custBlnAmount;
	}

	public void setCustBlnAmount(double custBlnAmount) {
		this.custBlnAmount = custBlnAmount;
	}

	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerAddress() {
		return ownerAddress;
	}
	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public String getOwnerPhNo() {
		return ownerPhNo;
	}
	public void setOwnerPhNo(String ownerPhNo) {
		this.ownerPhNo = ownerPhNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddrs() {
		return customerAddrs;
	}
	public void setCustomerAddrs(String customerAddrs) {
		this.customerAddrs = customerAddrs;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPhNo() {
		return customerPhNo;
	}
	public void setCustomerPhNo(String customerPhNo) {
		this.customerPhNo = customerPhNo;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

}
