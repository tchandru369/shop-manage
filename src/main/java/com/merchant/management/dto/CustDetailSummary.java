package com.merchant.management.dto;

import java.util.List;

public class CustDetailSummary {
	
	private String custAddress;
	private String custCity;
	private String custCountry;
	private String custCreatedDate;
	private String custDob;
	private String custGender;
	private String custState;
	private String custPincode;
	private String custModifiedDate;
	private List<UserCustBalDto> BalanceList;
	private List<UserCustLastTransaction> transactionList;
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
	public String getCustCountry() {
		return custCountry;
	}
	public void setCustCountry(String custCountry) {
		this.custCountry = custCountry;
	}
	public String getCustCreatedDate() {
		return custCreatedDate;
	}
	public void setCustCreatedDate(String custCreatedDate) {
		this.custCreatedDate = custCreatedDate;
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
	public String getCustState() {
		return custState;
	}
	public void setCustState(String custState) {
		this.custState = custState;
	}
	public String getCustPincode() {
		return custPincode;
	}
	public void setCustPincode(String custPincode) {
		this.custPincode = custPincode;
	}
	public String getCustModifiedDate() {
		return custModifiedDate;
	}
	public void setCustModifiedDate(String custModifiedDate) {
		this.custModifiedDate = custModifiedDate;
	}
	public List<UserCustBalDto> getBalanceList() {
		return BalanceList;
	}
	public void setBalanceList(List<UserCustBalDto> balanceList) {
		BalanceList = balanceList;
	}
	public List<UserCustLastTransaction> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<UserCustLastTransaction> transactionList) {
		this.transactionList = transactionList;
	}
	
	
	

}
