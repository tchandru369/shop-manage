package com.merchant.management.dto;

public class MerchantDetailRes {
	
	private String response;
	private String token;
	private String userName;
	private String userType;
	private String errorMsg;
	private String errorCode;
	private String custRefId;
	
	
	

	public String getCustRefId() {
		return custRefId;
	}

	public void setCustRefId(String custRefId) {
		this.custRefId = custRefId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getUserName() {
		return userName;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	

}
