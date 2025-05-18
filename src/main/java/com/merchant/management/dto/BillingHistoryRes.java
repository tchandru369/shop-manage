package com.merchant.management.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class BillingHistoryRes {
   
		
	private String custInvoiceIdRes;
	
	private String custEmailIdRes;
	
	private String custPhnNoRes;
	
	private double custPaidAmtRes;
	
	private double custDueAmtRes;
	
	private double custTotalAmtRes;
	
	private String custInvoiceDateRes;
	
	
	public BillingHistoryRes() {
		
	}
	public String getCustInvoiceIdRes() {
		return custInvoiceIdRes;
	}

	public void setCustInvoiceIdRes(String custInvoiceIdRes) {
		this.custInvoiceIdRes = custInvoiceIdRes;
	}

	public String getCustEmailIdRes() {
		return custEmailIdRes;
	}

	public void setCustEmailIdRes(String custEmailIdRes) {
		this.custEmailIdRes = custEmailIdRes;
	}

	public String getCustPhnNoRes() {
		return custPhnNoRes;
	}

	public void setCustPhnNoRes(String custPhnNoRes) {
		this.custPhnNoRes = custPhnNoRes;
	}

	public double getCustPaidAmtRes() {
		return custPaidAmtRes;
	}

	public void setCustPaidAmtRes(double custPaidAmtRes) {
		this.custPaidAmtRes = custPaidAmtRes;
	}

	public double getCustDueAmtRes() {
		return custDueAmtRes;
	}

	public void setCustDueAmtRes(double custDueAmtRes) {
		this.custDueAmtRes = custDueAmtRes;
	}

	public double getCustTotalAmtRes() {
		return custTotalAmtRes;
	}

	public void setCustTotalAmtRes(double custTotalAmtRes) {
		this.custTotalAmtRes = custTotalAmtRes;
	}

	public String getCustInvoiceDateRes() {
		return custInvoiceDateRes;
	}

	public void setCustInvoiceDateRes(String custInvoiceDateRes) {
		this.custInvoiceDateRes = custInvoiceDateRes;
	}

	public String getCustFullPaidFlgRes() {
		return custFullPaidFlgRes;
	}

	public void setCustFullPaidFlgRes(String custFullPaidFlgRes) {
		this.custFullPaidFlgRes = custFullPaidFlgRes;
	}

	private String custFullPaidFlgRes;
	

}
