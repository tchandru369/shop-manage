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
@Table(name = "order_details_tb")
public class ShopCustOrderDetails {
	
	@Id
	@GeneratedValue
	private int orderCustId;
	@Column(name = "order_cust_phone_no")
	private String orderCustPhoneNo;
	@Column(name = "order_cust_email_id")
	private String orderCustEmailId;
	@Column(name = "order_cust_owner_name")
	private String orderCustOwnerName;
	@Column(name = "order_cust_prod_cmp")
	private String orderCustProdCmp;
	@Column(name = "order_cust_prod_type")
	private String orderCustProdType;
	@Column(name = "order_cust_prod_name")
	private String orderCustProdName;
	@Column(name = "order_cust_prod_qty")
	private int orderCustProdQty;
	@Column(name = "order_cust_prod_price")
	private double orderCustProdPrice;
	@Column(name = "order_cust_crtd_date")
	private String orderCustCrtdDate;
	@Column(name = "order_cust_status")
	private String orderCustStatus;
	public String getOrderCustProdCmp() {
		return orderCustProdCmp;
	}
	public void setOrderCustProdCmp(String orderCustProdCmp) {
		this.orderCustProdCmp = orderCustProdCmp;
	}
	public int getOrderCustId() {
		return orderCustId;
	}
	public void setOrderCustId(int orderCustId) {
		this.orderCustId = orderCustId;
	}
	public String getOrderCustPhoneNo() {
		return orderCustPhoneNo;
	}
	public void setOrderCustPhoneNo(String orderCustPhoneNo) {
		this.orderCustPhoneNo = orderCustPhoneNo;
	}
	public String getOrderCustEmailId() {
		return orderCustEmailId;
	}
	public void setOrderCustEmailId(String orderCustEmailId) {
		this.orderCustEmailId = orderCustEmailId;
	}
	public String getOrderCustOwnerName() {
		return orderCustOwnerName;
	}
	public void setOrderCustOwnerName(String orderCustOwnerName) {
		this.orderCustOwnerName = orderCustOwnerName;
	}
	public String getOrderCustProdType() {
		return orderCustProdType;
	}
	public void setOrderCustProdType(String orderCustProdType) {
		this.orderCustProdType = orderCustProdType;
	}
	public String getOrderCustProdName() {
		return orderCustProdName;
	}
	public void setOrderCustProdName(String orderCustProdName) {
		this.orderCustProdName = orderCustProdName;
	}
	public int getOrderCustProdQty() {
		return orderCustProdQty;
	}
	public void setOrderCustProdQty(int orderCustProdQty) {
		this.orderCustProdQty = orderCustProdQty;
	}
	public double getOrderCustProdPrice() {
		return orderCustProdPrice;
	}
	public void setOrderCustProdPrice(double orderCustProdPrice) {
		this.orderCustProdPrice = orderCustProdPrice;
	}
	public String getOrderCustCrtdDate() {
		return orderCustCrtdDate;
	}
	public void setOrderCustCrtdDate(String orderCustCrtdDate) {
		this.orderCustCrtdDate = orderCustCrtdDate;
	}
	public String getOrderCustStatus() {
		return orderCustStatus;
	}
	public void setOrderCustStatus(String orderCustStatus) {
		this.orderCustStatus = orderCustStatus;
	}
	
	

}
