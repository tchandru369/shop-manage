package com.merchant.management.entity;

import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "billing_history")
public class BillingHistory {
	
	@Id
	@GeneratedValue
	private long billingId;
	@Column(name = "cust_invoice_id")
	private String custInvoiceId;
	@Column(name = "cust_email_id")
	private String custEmailId;
	@Column(name = "cust_Phone_No")
	private String custPhnNo;
	@Column(name = "cust_Paid_Amt")
	private String custPaidAmt;
	@Column(name = "cust_Due_Amt")
	private String custDueAmt;
	@Column(name = "cust_Total_Amt")
	private String custTotalAmt;
	@Column(name = "cust_invoice_date")
	private String custInvoiceDate;
	@Column(name = "cust_fullyPaid_Flg")
	private String custFullyPaidFlg;
	@Column(name = "cust_shop_email_id")
	private String custShopEmailId;
	
	public String getCustInvoiceId() {
		return custInvoiceId;
	}
	public void setCustInvoiceId(String custInvoiceId) {
		this.custInvoiceId = custInvoiceId;
	}
	public String getCutEmailId() {
		return custEmailId;
	}
	public void setCutEmailId(String custEmailId) {
		this.custEmailId = custEmailId;
	}
	public String getCustPhnNo() {
		return custPhnNo;
	}
	public void setCustPhnNo(String custPhnNo) {
		this.custPhnNo = custPhnNo;
	}
	public String getCustPaidAmt() {
		return custPaidAmt;
	}
	public void setCustPaidAmt(String custPaidAmt) {
		this.custPaidAmt = custPaidAmt;
	}
	public String getCustDueAmt() {
		return custDueAmt;
	}
	public void setCustDueAmt(String custDueAmt) {
		this.custDueAmt = custDueAmt;
	}
	public String getCustTotalAmt() {
		return custTotalAmt;
	}
	public void setCustTotalAmt(String custTotalAmt) {
		this.custTotalAmt = custTotalAmt;
	}
	public String getCustInvoiceDate() {
		return custInvoiceDate;
	}
	public void setCustInvoiceDate(String custInvoiceDate) {
		this.custInvoiceDate = custInvoiceDate;
	}
	public String getCustFullyPaidFlg() {
		return custFullyPaidFlg;
	}
	public void setCustFullyPaidFlg(String custFullyPaidFlg) {
		this.custFullyPaidFlg = custFullyPaidFlg;
	}
	public String getCustShopEmailId() {
		return custShopEmailId;
	}
	public void setCustShopEmailId(String custShopEmailId) {
		this.custShopEmailId = custShopEmailId;
	}

}
