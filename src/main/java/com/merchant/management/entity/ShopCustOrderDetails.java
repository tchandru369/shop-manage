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
	@Column(name = "order_cust_prod_cmp")
	private String orderCustProdCmp;
	@Column(name = "order_cust_prod_type")
	private String orderCustProdType;
	@Column(name = "order_cust_prod_name")
	private String orderCustProdName;
	@Column(name = "order_cust_prod_qty")
	private int orderCustProdQty;
	@Column(name = "order_cust_ref_id")
	private String orderCustRefId;
	@Column(name = "order_cust_prod_price")
	private double orderCustProdPrice;
	@Column(name = "order_cust_crtd_date")
	private String orderCustCrtdDate;
	
	
	public String getOrderCustRefId() {
		return orderCustRefId;
	}
	public void setOrderCustRefId(String orderCustRefId) {
		this.orderCustRefId = orderCustRefId;
	}
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
	
	

}
