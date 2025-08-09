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
	@Column(name="cust_order_emailid")
	private String custOrderEmailId;
	@Column(name="cust_order_owner_name")
	private String custOrderOwnerName;
	@Column(name="cust_order_ph_no")
	private String custOrderPhNo;
	@Column(name="cust_order_prod_cmp")
	private String custOrderProdCmp;
	@Column(name="cust_order_prod_type")
	private String custOrderProdType;
	@Column(name="cust_order_prod_name")
	private String custOrderProdName;
	@Column(name="cust_order_prod_qty")
	private int custOrderProdQty;
	@Column(name="cust_order_req_status")
	private String custOrderReqStatus;
	@Column(name="cust_order_live_flg")
	private String custOrderLiveFlg;
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
	public String getCustOrderEmailId() {
		return custOrderEmailId;
	}
	public void setCustOrderEmailId(String custOrderEmailId) {
		this.custOrderEmailId = custOrderEmailId;
	}
	public String getCustOrderOwnerName() {
		return custOrderOwnerName;
	}
	public void setCustOrderOwnerName(String custOrderOwnerName) {
		this.custOrderOwnerName = custOrderOwnerName;
	}
	public String getCustOrderPhNo() {
		return custOrderPhNo;
	}
	public void setCustOrderPhNo(String custOrderPhNo) {
		this.custOrderPhNo = custOrderPhNo;
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
	public String getCustOrderReqStatus() {
		return custOrderReqStatus;
	}
	public void setCustOrderReqStatus(String custOrderReqStatus) {
		this.custOrderReqStatus = custOrderReqStatus;
	}
	public String getCustOrderLiveFlg() {
		return custOrderLiveFlg;
	}
	public void setCustOrderLiveFlg(String custOrderLiveFlg) {
		this.custOrderLiveFlg = custOrderLiveFlg;
	}
	
	
	
	
}
