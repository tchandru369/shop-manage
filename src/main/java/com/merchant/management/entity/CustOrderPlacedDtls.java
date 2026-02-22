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
@Table(name = "cust_order_placed_tb")
public class CustOrderPlacedDtls {
 
	@Id
	@GeneratedValue
	private int custOrdId;
	@Column(name="cust_order_name")
	private String custOrderName;
	@Column(name="cust_order_email")
	private String custOrderEmail;
	@Column(name="cust_order_phno")
	private String custOrderPhNo;
	@Column(name="cust_customerRef_id")
	private String custCustRefId;
	@Column(name="cust_order_owner_ref_id")
	private String custOrderOwnerRefId;
	@Column(name="cust_order_type")
	private String custOrderType;
	@Column(name="cust_order_ref_id")
	private String custOrderRefId;
	@Column(name="cust_pymt_ref_id")
	private String custPymtRefId;
	@Column(name="cust_order_crtd_date")
	private String custOrderCrtdDate;
	@Column(name="cust_order_req_status")
	private String custOrderReqStatus;
	@Column(name="cust_order_live_flg")
	private String custOrderLiveFlg;
	
	
	
	public String getCustCustRefId() {
		return custCustRefId;
	}
	public void setCustCustRefId(String custCustRefId) {
		this.custCustRefId = custCustRefId;
	}
	public String getCustPymtRefId() {
		return custPymtRefId;
	}
	public void setCustPymtRefId(String custPymtRefId) {
		this.custPymtRefId = custPymtRefId;
	}
	public String getCustOrderRefId() {
		return custOrderRefId;
	}
	public void setCustOrderRefId(String custOrderRefId) {
		this.custOrderRefId = custOrderRefId;
	}
	public int getCustOrdId() {
		return custOrdId;
	}
	public void setCustOrdId(int custOrdId) {
		this.custOrdId = custOrdId;
	}
	public String getCustOrderName() {
		return custOrderName;
	}
	public void setCustOrderName(String custOrderName) {
		this.custOrderName = custOrderName;
	}
	public String getCustOrderEmail() {
		return custOrderEmail;
	}
	public void setCustOrderEmail(String custOrderEmail) {
		this.custOrderEmail = custOrderEmail;
	}
	public String getCustOrderPhNo() {
		return custOrderPhNo;
	}
	public void setCustOrderPhNo(String custOrderPhNo) {
		this.custOrderPhNo = custOrderPhNo;
	}
	public String getCustOrderOwnerRefId() {
		return custOrderOwnerRefId;
	}
	public void setCustOrderOwnerRefId(String custOrderOwnerRefId) {
		this.custOrderOwnerRefId = custOrderOwnerRefId;
	}
	public String getCustOrderType() {
		return custOrderType;
	}
	public void setCustOrderType(String custOrderType) {
		this.custOrderType = custOrderType;
	}
	public String getCustOrderCrtdDate() {
		return custOrderCrtdDate;
	}
	public void setCustOrderCrtdDate(String custOrderCrtdDate) {
		this.custOrderCrtdDate = custOrderCrtdDate;
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
