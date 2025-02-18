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
	private String discountAmount;
	
	public PdfDetails() {
		
	}
	
	public PdfDetails(String ownerName, String ownerAddress, String ownerEmail, String ownerPhNo, String customerName,
			String customerAddrs, String customerEmail, String customerPhNo, String invoiceNumber,String discountAmount) {
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
		this.discountAmount = discountAmount;
	}
	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
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
