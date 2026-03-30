package com.merchant.management.dto;

public class MyRequestNotifyDto {
	
	private String recentReqCount;
	private String processOdrCount;
	private String balCustPaidCount;
	private String custReqCount;
	private String custVerCount;
	
	public MyRequestNotifyDto() {
		
	}
	public MyRequestNotifyDto(String recentReqCount, String processOdrCount,String balCustPaidCount, String custReqCount, String custVerCount) {
		super();
		this.recentReqCount = recentReqCount;
		this.processOdrCount = processOdrCount;
		this.balCustPaidCount = balCustPaidCount;
		this.custReqCount = custReqCount;
		this.custVerCount = custVerCount;
	}
	
	public String getBalCustPaidCount() {
		return balCustPaidCount;
	}
	public void setBalCustPaidCount(String balCustPaidCount) {
		this.balCustPaidCount = balCustPaidCount;
	}
	public String getRecentReqCount() {
		return recentReqCount;
	}
	public void setRecentReqCount(String recentReqCount) {
		this.recentReqCount = recentReqCount;
	}
	public String getProcessOdrCount() {
		return processOdrCount;
	}
	public void setProcessOdrCount(String processOdrCount) {
		this.processOdrCount = processOdrCount;
	}
	public String getCustReqCount() {
		return custReqCount;
	}
	public void setCustReqCount(String custReqCount) {
		this.custReqCount = custReqCount;
	}
	public String getCustVerCount() {
		return custVerCount;
	}
	public void setCustVerCount(String custVerCount) {
		this.custVerCount = custVerCount;
	}
	
	

}
