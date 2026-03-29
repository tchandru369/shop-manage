package com.merchant.management.dto;

public class OwnerRemResponseDto {

	private String errorCode;
	private String response;
	private String errorMsg;
	private String remSent;
	private String remAllSent;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getRemSent() {
		return remSent;
	}
	public void setRemSent(String remSent) {
		this.remSent = remSent;
	}
	public String getRemAllSent() {
		return remAllSent;
	}
	public void setRemAllSent(String remAllSent) {
		this.remAllSent = remAllSent;
	}
	
	
	
}
