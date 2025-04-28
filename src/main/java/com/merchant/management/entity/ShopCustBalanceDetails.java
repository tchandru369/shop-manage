package com.merchant.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_balance_details_tb")
public class ShopCustBalanceDetails {

	@Id
	@GeneratedValue
	private int shopCustBlnId;
	@Column(name = "cust_bal_name")
	private String custBalName;
	@Column(name = "cust_bal_phone_no")
	private String custBalPhoneNo;
	@Column(name = "cust_bal_email_id")
	private String custBalEmailId;
	@Column(name = "cust_bal_owner_name")
	private String custBalOwnerName;
	@Column(name = "cust_bal_amt")
	private double custBalAmt;
	@Column(name = "cust_bal_act_amt")
	private double custBalActAmt;
	@Column(name = "cust_bal_paid_amt")
	private double custBalPaidAmt;
	@Column(name = "cust_bal_date")
	private String custBalDate;
	@Column(name = "cust_bal_ref_no")
	private String custBalRefNo;
	public int getShopCustBlnId() {
		return shopCustBlnId;
	}
	public void setShopCustBlnId(int shopCustBlnId) {
		this.shopCustBlnId = shopCustBlnId;
	}
	public String getCustBalName() {
		return custBalName;
	}
	public void setCustBalName(String custBalName) {
		this.custBalName = custBalName;
	}
	public String getCustBalPhoneNo() {
		return custBalPhoneNo;
	}
	public void setCustBalPhoneNo(String custBalPhoneNo) {
		this.custBalPhoneNo = custBalPhoneNo;
	}
	public String getCustBalEmailId() {
		return custBalEmailId;
	}
	public void setCustBalEmailId(String custBalEmailId) {
		this.custBalEmailId = custBalEmailId;
	}
	public String getCustBalOwnerName() {
		return custBalOwnerName;
	}
	public void setCustBalOwnerName(String custBalOwnerName) {
		this.custBalOwnerName = custBalOwnerName;
	}
	public double getCustBalAmt() {
		return custBalAmt;
	}
	public void setCustBalAmt(double custBalAmt) {
		this.custBalAmt = custBalAmt;
	}
	public double getCustBalActAmt() {
		return custBalActAmt;
	}
	public void setCustBalActAmt(double custBalActAmt) {
		this.custBalActAmt = custBalActAmt;
	}
	public double getCustBalPaidAmt() {
		return custBalPaidAmt;
	}
	public void setCustBalPaidAmt(double custBalPaidAmt) {
		this.custBalPaidAmt = custBalPaidAmt;
	}
	public String getCustBalDate() {
		return custBalDate;
	}
	public void setCustBalDate(String custBalDate) {
		this.custBalDate = custBalDate;
	}
	public String getCustBalRefNo() {
		return custBalRefNo;
	}
	public void setCustBalRefNo(String custBalRefNo) {
		this.custBalRefNo = custBalRefNo;
	}
}
