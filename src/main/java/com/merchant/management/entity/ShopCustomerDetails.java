package com.merchant.management.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "shop_customer_details_tb")
public class ShopCustomerDetails{

	@Id
	@GeneratedValue
	private int shopCustId;
	@Column(name = "cust_name")
	private String custName;
	@Column(name = "cust_phone_no")
	private String custPhoneNo;
	@Column(name = "cust_address")
	private String custAddress;
	@Column(name = "cust_city")
	private String custCity;
	@Column(name = "cust_country")
	private String custCountry;
	@Column(name = "cust_state")
	private String custState;
	@Column(name = "cust_pincode")
	private String custPinCode;
	@Column(name = "cust_dob")
	private String custDob;
	@Column(name = "cust_gender")
	private String custGender;
	@Column(name = "cust_email_id")
	private String custEmailId;
	@Column(name = "cust_pan_no")
	private String custPanNo;
	@Column(name = "cust_type")
	private String custType;
	@Column(name = "cust_created_date")
	private String custCreatedDate;
	@Column(name = "cust_modified_date")
	private String custModifiedDate;
	@Column(name = "cust_owner_details")
	private String custOwmerDetails;
	@Column(name = "cust_live")
	private String custLive;
	@Column(name = "cust_balance_flg")
	private String custBalanceFlg;
	@Column(name = "cust_password")
	private String custPassword;
	
	
	
	
	public String getCustCountry() {
		return custCountry;
	}
	public void setCustCountry(String custCountry) {
		this.custCountry = custCountry;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public int getShopCustId() {
		return shopCustId;
	}
	public void setShopCustId(int shopCustId) {
		this.shopCustId = shopCustId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustPhoneNo() {
		return custPhoneNo;
	}
	public void setCustPhoneNo(String custPhoneNo) {
		this.custPhoneNo = custPhoneNo;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getCustCity() {
		return custCity;
	}
	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}
	public String getCustState() {
		return custState;
	}
	public void setCustState(String custState) {
		this.custState = custState;
	}
	public String getCustPinCode() {
		return custPinCode;
	}
	public void setCustPinCode(String custPinCode) {
		this.custPinCode = custPinCode;
	}
	public String getCustDob() {
		return custDob;
	}
	public void setCustDob(String custDob) {
		this.custDob = custDob;
	}
	public String getCustGender() {
		return custGender;
	}
	public void setCustGender(String custGender) {
		this.custGender = custGender;
	}
	public String getCustEmailId() {
		return custEmailId;
	}
	public void setCustEmailId(String custEmailId) {
		this.custEmailId = custEmailId;
	}
	public String getCustPanNo() {
		return custPanNo;
	}
	public void setCustPanNo(String custPanNo) {
		this.custPanNo = custPanNo;
	}
	public String getCustCreatedDate() {
		return custCreatedDate;
	}
	public void setCustCreatedDate(String custCreatedDate) {
		this.custCreatedDate = custCreatedDate;
	}
	public String getCustModifiedDate() {
		return custModifiedDate;
	}
	public void setCustModifiedDate(String custModifiedDate) {
		this.custModifiedDate = custModifiedDate;
	}
	public String getCustOwmerDetails() {
		return custOwmerDetails;
	}
	public void setCustOwmerDetails(String custOwmerDetails) {
		this.custOwmerDetails = custOwmerDetails;
	}
	public String getCustLive() {
		return custLive;
	}
	public void setCustLive(String custLive) {
		this.custLive = custLive;
	}
	public String getCustBalanceFlg() {
		return custBalanceFlg;
	}
	public void setCustBalanceFlg(String custBalanceFlg) {
		this.custBalanceFlg = custBalanceFlg;
	}
	public String getCustPassword() {
		return custPassword;
	}
	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}
	

}
