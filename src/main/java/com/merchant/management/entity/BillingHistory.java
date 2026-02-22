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
	private double custPaidAmt;
	@Column(name = "cust_Due_Amt")
	private double custDueAmt;
	@Column(name = "cust_Total_Amt")
	private double custTotalAmt;
	@Column(name = "cust_invoice_date")
	private String custInvoiceDate;
	@Column(name = "cust_fullyPaid_Flg")
	private String custFullyPaidFlg;
	@Column(name = "cust_owner_email_id")
	private String custOwnerEmailId;
	@Column(name = "shop_cust_ref_id")
	private String shopCustRefId;
	@Column(name = "shop_owner_ref_id")
	private String shopOwnerRefId;
	@Column(name = "shop_order_ref_id")
	private String shopOrderRefId;
	@Column(name = "cust_pymt_ref_id")
	private String custPymtRefId;
	
	
	
	
	
	public String getCustPymtRefId() {
		return custPymtRefId;
	}
	public void setCustPymtRefId(String custPymtRefId) {
		this.custPymtRefId = custPymtRefId;
	}
	public long getBillingId() {
		return billingId;
	}
	public void setBillingId(long billingId) {
		this.billingId = billingId;
	}
	public String getCustEmailId() {
		return custEmailId;
	}
	public void setCustEmailId(String custEmailId) {
		this.custEmailId = custEmailId;
	}
	public String getShopCustRefId() {
		return shopCustRefId;
	}
	public void setShopCustRefId(String shopCustRefId) {
		this.shopCustRefId = shopCustRefId;
	}
	public String getShopOwnerRefId() {
		return shopOwnerRefId;
	}
	public void setShopOwnerRefId(String shopOwnerRefId) {
		this.shopOwnerRefId = shopOwnerRefId;
	}
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
	public double getCustPaidAmt() {
		return custPaidAmt;
	}
	public void setCustPaidAmt(double custPaidAmt) {
		this.custPaidAmt = custPaidAmt;
	}
	public double getCustDueAmt() {
		return custDueAmt;
	}
	public void setCustDueAmt(double custDueAmt) {
		this.custDueAmt = custDueAmt;
	}
	public double getCustTotalAmt() {
		return custTotalAmt;
	}
	public void setCustTotalAmt(double custTotalAmt) {
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
	public String getCustOwnerEmailId() {
		return custOwnerEmailId;
	}
	public void setCustOwnerEmailId(String custOwnerEmailId) {
		this.custOwnerEmailId = custOwnerEmailId;
	}
	public String getShopOrderRefId() {
		return shopOrderRefId;
	}
	public void setShopOrderRefId(String shopOrderRefId) {
		this.shopOrderRefId = shopOrderRefId;
	}
	

}
