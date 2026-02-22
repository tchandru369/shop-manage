package com.merchant.management.entity;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.merchant.management.dto.PdfProductDetails;
import com.merchant.management.entity.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "billing_details")
public class BillingEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int billing_id;
	@Column(name="billing_customer_name")
	private String billingCustomerName;
	@Column(name="billing_no")
	private String billingNo;
	@Column(name="billing_customer_email")
	private String billingCustomerEmail;
	@Column(name="billing_customer_phno")
	private String billingCustomerPhNo;
	@Column(name="billing_customer_address")
	private String billingCustomerAddress;
	@Column(name="billing_date")
	private String billingDate;
	@Column(name="billing_time")
	private String billingTime;
	@Column(name="billing_date_time")
	private String billingDateTime;
	@Column(name="billing_total_prd_qty")
	private String billingTotalProductQty;
	@Column(name="billing_total_price")
	private String billingTotalPrice;
	@Column(name="billing_total_price_tax")
	private String billingTotalPriceTax;
	@Column(name = "billing_due_price")
	private String billingDuePrice;
	@Column(name = "billing_amt_paid")
	private String billingAmtPaid;
	@Column(name = "billing_due_flag")
	private String billingDueFlag;
	
	
	
	
	


//	public BillingEntity() {
//		
//	}
//	
//	
//	public BillingEntity(String billingCustomerName, String billingCustomerEmail, String billingCustomerPhNo,
//			String billingCustomerAddress, String billingDate, String billingTime, String billingDateTime,
//			String billingTotalProductQty, String billingTotalPrice, String billingTotalPriceTax, String billingNo) {
//		super();
//		this.billingCustomerName = billingCustomerName;
//		this.billingCustomerEmail = billingCustomerEmail;
//		this.billingCustomerPhNo = billingCustomerPhNo;
//		this.billingCustomerAddress = billingCustomerAddress;
//		this.billingDate = billingDate;
//		this.billingTime = billingTime;
//		this.billingDateTime = billingDateTime;
//		this.billingTotalProductQty = billingTotalProductQty;
//		this.billingTotalPrice = billingTotalPrice;
//		this.billingTotalPriceTax = billingTotalPriceTax;
//		this.billingNo = billingNo;
//	}
	public int getBilling_id() {
		return billing_id;
	}
	public String getBillingNo() {
		return billingNo;
	}


	public void setBillingNo(String billingNo) {
		this.billingNo = billingNo;
	}
	public String getBillingAmtPaid() {
		return billingAmtPaid;
	}
	public void setBillingAmtPaid(String billingAmtPaid) {
		this.billingAmtPaid = billingAmtPaid;
	}
	public String getBillingDueFlag() {
		return billingDueFlag;
	}
	public void setBillingDueFlag(String billingDueFlag) {
		this.billingDueFlag = billingDueFlag;
	}

	public void setBilling_id(int billing_id) {
		this.billing_id = billing_id;
	}
	public String getBillingCustomerName() {
		return billingCustomerName;
	}
	public void setBillingCustomerName(String billingCustomerName) {
		this.billingCustomerName = billingCustomerName;
	}
	public String getBillingCustomerEmail() {
		return billingCustomerEmail;
	}
	public void setBillingCustomerEmail(String billingCustomerEmail) {
		this.billingCustomerEmail = billingCustomerEmail;
	}
	public String getBillingCustomerPhNo() {
		return billingCustomerPhNo;
	}
	public void setBillingCustomerPhNo(String billingCustomerPhNo) {
		this.billingCustomerPhNo = billingCustomerPhNo;
	}
	public String getBillingCustomerAddress() {
		return billingCustomerAddress;
	}
	public void setBillingCustomerAddress(String billingCustomerAddress) {
		this.billingCustomerAddress = billingCustomerAddress;
	}
	public String getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(String billingDate) {
		this.billingDate = billingDate;
	}
	public String getBillingTime() {
		return billingTime;
	}
	public void setBillingTime(String billingTime) {
		this.billingTime = billingTime;
	}
	public String getBillingDateTime() {
		return billingDateTime;
	}
	public void setBillingDateTime(String billingDateTime) {
		this.billingDateTime = billingDateTime;
	}
	public String getBillingTotalProductQty() {
		return billingTotalProductQty;
	}
	public void setBillingTotalProductQty(String billingTotalProductQty) {
		this.billingTotalProductQty = billingTotalProductQty;
	}
	public String getBillingTotalPrice() {
		return billingTotalPrice;
	}
	public void setBillingTotalPrice(String billingTotalPrice) {
		this.billingTotalPrice = billingTotalPrice;
	}
	public String getBillingTotalPriceTax() {
		return billingTotalPriceTax;
	}
	public void setBillingTotalPriceTax(String billingTotalPriceTax) {
		this.billingTotalPriceTax = billingTotalPriceTax;
	}
	
	public String getBillingDuePrice() {
		return billingDuePrice;
	}
	public void setBillingDuePrice(String billingDuePrice) {
		this.billingDuePrice = billingDuePrice;
	}
}
