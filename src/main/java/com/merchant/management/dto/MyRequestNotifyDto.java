package com.merchant.management.dto;

public class MyRequestNotifyDto {
	
	private String recentReqCount;
	private String processOdrCount;
	private String custReqCount;
	private String custVerCount;
	
	public MyRequestNotifyDto() {
		
	}
	public MyRequestNotifyDto(String recentReqCount, String processOdrCount, String custReqCount, String custVerCount) {
		super();
		this.recentReqCount = recentReqCount;
		this.processOdrCount = processOdrCount;
		this.custReqCount = custReqCount;
		this.custVerCount = custVerCount;
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
