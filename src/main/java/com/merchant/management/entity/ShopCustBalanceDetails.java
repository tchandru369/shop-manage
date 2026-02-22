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
	@Column(name = "cust_bal_cust_ref_id")
	private String custBalCustRefId;
	@Column(name = "cust_bal_owner_ref_id")
	private String custBalOwnerRefId;
	@Column(name = "cust_bal_amt")
	private double custBalAmt;
	@Column(name = "cust_bal_act_amt")
	private double custBalActAmt;
	@Column(name = "cust_bal_paid_amt")
	private double custBalPaidAmt;
	@Column(name = "cust_bal_date")
	private String custBalDate;
	@Column(name = "cust_bal_order_ref_id")
	private String custBalOrderRefId;
	@Column(name = "cust_bal_pymt_ref_id")
	private String custBalPymtRefId;
	@Column(name = "cust_bal_order_status")
	private String custBalStatus;
	
	
	
	public String getCustBalOrderRefId() {
		return custBalOrderRefId;
	}
	public void setCustBalOrderRefId(String custBalOrderRefId) {
		this.custBalOrderRefId = custBalOrderRefId;
	}
	public String getCustBalPymtRefId() {
		return custBalPymtRefId;
	}
	public void setCustBalPymtRefId(String custBalPymtRefId) {
		this.custBalPymtRefId = custBalPymtRefId;
	}
	public String getCustBalStatus() {
		return custBalStatus;
	}
	public void setCustBalStatus(String custBalStatus) {
		this.custBalStatus = custBalStatus;
	}
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
	
	public String getCustBalCustRefId() {
		return custBalCustRefId;
	}
	public void setCustBalCustRefId(String custBalCustRefId) {
		this.custBalCustRefId = custBalCustRefId;
	}
	public String getCustBalOwnerRefId() {
		return custBalOwnerRefId;
	}
	public void setCustBalOwnerRefId(String custBalOwnerRefId) {
		this.custBalOwnerRefId = custBalOwnerRefId;
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
}
