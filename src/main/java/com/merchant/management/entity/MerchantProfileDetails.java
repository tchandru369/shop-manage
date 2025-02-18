package com.merchant.management.entity;

public class MerchantProfileDetails {
  
	private String merchantUserName;
	private String merchantEmail;
	private String merchantPhoneNumber;
	private String merchantAddress;
	private String merchantUserType;
	
	
	public MerchantProfileDetails(String merchantUserName, String merchantEmail, String merchantPhoneNumber,
			String merchantAddress, String merchantUserType) {
		super();
		this.merchantUserName = merchantUserName;
		this.merchantEmail = merchantEmail;
		this.merchantPhoneNumber = merchantPhoneNumber;
		this.merchantAddress = merchantAddress;
		this.merchantUserType = merchantUserType;
	}
	
	public MerchantProfileDetails() {
		
	}
	public String getMerchantUserName() {
		return merchantUserName;
	}
	public void setMerchantUserName(String merchantUserName) {
		this.merchantUserName = merchantUserName;
	}
	public String getMerchantEmail() {
		return merchantEmail;
	}
	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}
	public String getMerchantPhoneNumber() {
		return merchantPhoneNumber;
	}
	public void setMerchantPhoneNumber(String merchantPhoneNumber) {
		this.merchantPhoneNumber = merchantPhoneNumber;
	}
	public String getMerchantAddress() {
		return merchantAddress;
	}
	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}
	public String getMerchantUserType() {
		return merchantUserType;
	}
	public void setMerchantUserType(String merchantUserType) {
		this.merchantUserType = merchantUserType;
	}

}
