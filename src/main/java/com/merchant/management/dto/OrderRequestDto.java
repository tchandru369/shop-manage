package com.merchant.management.dto;

import java.util.List;

public class OrderRequestDto {
	
	private String orderCustName;
	private String orderCustPhoneNo;
	private String orderCustCrtdDate;
	private String orderOwnerRefId;
	private String orderCustEmailId;
	private String orderCustType;
	private String orderCustRefId;
	private String orderPymtRefId;
	private String orderReqStatus;
	private String noteToPayer;
	private String orderRefId;
	private double orderCustTotalPrice;
	private double orderFinalAmtPaid;
	
	private List<MilkOrderList> orderList;
	
	public String getOrderRefId() {
		return orderRefId;
	}

	public void setOrderRefId(String orderRefId) {
		this.orderRefId = orderRefId;
	}

	public String getOrderCustRefId() {
		return orderCustRefId;
	}

	public void setOrderCustRefId(String orderCustRefId) {
		this.orderCustRefId = orderCustRefId;
	}

	public String getOrderPymtRefId() {
		return orderPymtRefId;
	}

	public void setOrderPymtRefId(String orderPymtRefId) {
		this.orderPymtRefId = orderPymtRefId;
	}

	public String getNoteToPayer() {
		return noteToPayer;
	}

	public void setNoteToPayer(String noteToPayer) {
		this.noteToPayer = noteToPayer;
	}

	public String getOrderReqStatus() {
		return orderReqStatus;
	}

	public void setOrderReqStatus(String orderReqStatus) {
		this.orderReqStatus = orderReqStatus;
	}

	public double getOrderFinalAmtPaid() {
		return orderFinalAmtPaid;
	}

	public void setOrderFinalAmtPaid(double orderFinalAmtPaid) {
		this.orderFinalAmtPaid = orderFinalAmtPaid;
	}

	public List<MilkOrderList> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<MilkOrderList> orderList) {
		this.orderList = orderList;
	}

	public OrderRequestDto(String orderCustName, String orderCustPhoneNo, String orderCustCrtdDate,
			String orderOwnerRefId, String orderCustEmailId, String orderCustType, double orderCustTotalPrice,String orderReqStatus) {
		super();
		this.orderCustName = orderCustName;
		this.orderCustPhoneNo = orderCustPhoneNo;
		this.orderCustCrtdDate = orderCustCrtdDate;
		this.orderOwnerRefId = orderOwnerRefId;
		this.orderCustEmailId = orderCustEmailId;
		this.orderCustType = orderCustType;
		this.orderCustTotalPrice = orderCustTotalPrice;
		this.orderReqStatus = orderReqStatus;
	}
	
	public OrderRequestDto() {
		
	}
	public String getOrderCustName() {
		return orderCustName;
	}
	public void setOrderCustName(String orderCustName) {
		this.orderCustName = orderCustName;
	}
	public String getOrderCustPhoneNo() {
		return orderCustPhoneNo;
	}
	public void setOrderCustPhoneNo(String orderCustPhoneNo) {
		this.orderCustPhoneNo = orderCustPhoneNo;
	}
	public String getOrderCustCrtdDate() {
		return orderCustCrtdDate;
	}
	public void setOrderCustCrtdDate(String orderCustCrtdDate) {
		this.orderCustCrtdDate = orderCustCrtdDate;
	}
	public String getOrderOwnerRefId() {
		return orderOwnerRefId;
	}
	public void setOrderOwnerRefId(String orderOwnerRefId) {
		this.orderOwnerRefId = orderOwnerRefId;
	}
	public String getOrderCustEmailId() {
		return orderCustEmailId;
	}
	public void setOrderCustEmailId(String orderCustEmailId) {
		this.orderCustEmailId = orderCustEmailId;
	}
	public String getOrderCustType() {
		return orderCustType;
	}
	public void setOrderCustType(String orderCustType) {
		this.orderCustType = orderCustType;
	}
	public double getOrderCustTotalPrice() {
		return orderCustTotalPrice;
	}
	public void setOrderCustTotalPrice(double orderCustTotalPrice) {
		this.orderCustTotalPrice = orderCustTotalPrice;
	}
	
	

}
