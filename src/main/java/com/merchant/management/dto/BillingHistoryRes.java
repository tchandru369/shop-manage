package com.merchant.management.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class BillingHistoryRes {
   
		
	private String custInvoiceIdRes;
	
	private String custEmailIdRes;
	
	private String custPhnNoRes;
	
	private String custPaidAmtRes;
	
	private String custDueAmtRes;
	
	private String custTotalAmtRes;
	
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

	public String getCustPaidAmtRes() {
		return custPaidAmtRes;
	}

	public void setCustPaidAmtRes(String custPaidAmtRes) {
		this.custPaidAmtRes = custPaidAmtRes;
	}

	public String getCustDueAmtRes() {
		return custDueAmtRes;
	}

	public void setCustDueAmtRes(String custDueAmtRes) {
		this.custDueAmtRes = custDueAmtRes;
	}

	public String getCustTotalAmtRes() {
		return custTotalAmtRes;
	}

	public void setCustTotalAmtRes(String custTotalAmtRes) {
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
