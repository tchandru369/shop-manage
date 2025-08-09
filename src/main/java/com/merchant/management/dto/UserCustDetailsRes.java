package com.merchant.management.dto;

public class UserCustDetailsRes {
	
	private String custName;
	private String custEmail;
	private String custType;
	private String custPhoneNo;
	private String custBalanceFlg;

	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getCustPhoneNo() {
		return custPhoneNo;
	}
	public void setCustPhoneNo(String custPhoneNo) {
		this.custPhoneNo = custPhoneNo;
	}
	public String getCustBalanceFlg() {
		return custBalanceFlg;
	}
	public void setCustBalanceFlg(String custBalanceFlg) {
		this.custBalanceFlg = custBalanceFlg;
	}	
}
