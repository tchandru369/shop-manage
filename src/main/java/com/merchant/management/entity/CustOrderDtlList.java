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
@Table(name = "cust_order_dtl_list_tb")
public class CustOrderDtlList {

	@Id
	@GeneratedValue
	private int custOrderListId;
	@Column(name="cust_order_crd_date")
	private String custOrderCrdDate;
	@Column(name="cust_order_prod_cmp")
	private String custOrderProdCmp;
	@Column(name="cust_order_ref_id")
	private String custOrderRefId;
	@Column(name="cust_order_prod_type")
	private String custOrderProdType;
	@Column(name="cust_order_prod_name")
	private String custOrderProdName;
	@Column(name="cust_order_prod_qty")
	private int custOrderProdQty;
	@Column(name="cust_order_live_flg")
	private String custOrderLiveFlg;
	
	
	
	
	public String getCustOrderRefId() {
		return custOrderRefId;
	}
	public void setCustOrderRefId(String custOrderRefId) {
		this.custOrderRefId = custOrderRefId;
	}
	public int getCustOrderListId() {
		return custOrderListId;
	}
	public void setCustOrderListId(int custOrderListId) {
		this.custOrderListId = custOrderListId;
	}
	public String getCustOrderCrdDate() {
		return custOrderCrdDate;
	}
	public void setCustOrderCrdDate(String custOrderCrdDate) {
		this.custOrderCrdDate = custOrderCrdDate;
	}
	public String getCustOrderProdCmp() {
		return custOrderProdCmp;
	}
	public void setCustOrderProdCmp(String custOrderProdCmp) {
		this.custOrderProdCmp = custOrderProdCmp;
	}
	public String getCustOrderProdType() {
		return custOrderProdType;
	}
	public void setCustOrderProdType(String custOrderProdType) {
		this.custOrderProdType = custOrderProdType;
	}
	public String getCustOrderProdName() {
		return custOrderProdName;
	}
	public void setCustOrderProdName(String custOrderProdName) {
		this.custOrderProdName = custOrderProdName;
	}
	public int getCustOrderProdQty() {
		return custOrderProdQty;
	}
	public void setCustOrderProdQty(int custOrderProdQty) {
		this.custOrderProdQty = custOrderProdQty;
	}
	public String getCustOrderLiveFlg() {
		return custOrderLiveFlg;
	}
	public void setCustOrderLiveFlg(String custOrderLiveFlg) {
		this.custOrderLiveFlg = custOrderLiveFlg;
	}
	
	
	
	
}
