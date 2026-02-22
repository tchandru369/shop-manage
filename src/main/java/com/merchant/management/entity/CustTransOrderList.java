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
@Table(name = "cust_trans_order_list")
public class CustTransOrderList {
	
	@Id
	@GeneratedValue
	private int custTransListId;
	@Column(name="trans_pymt_date")
	private String transPymtDate;
	@Column(name="trans_order_prod_cmp")
	private String transOrderProdCmp;
	@Column(name="trans_order_prod_type")
	private String transOrderProdType;
	@Column(name="trans_order_prod_name")
	private String transOrderProdName;
	@Column(name="trans_order_prod_qty")
	private int transOrderProdQty;
	@Column(name="trans_pymt_ref_id")
	private String transPymtRefId;
	@Column(name="trans_cust_order_ref_id")
	private String transCustOrderRefId;
	public int getCustTransListId() {
		return custTransListId;
	}
	public void setCustTransListId(int custTransListId) {
		this.custTransListId = custTransListId;
	}
	public String getTransPymtDate() {
		return transPymtDate;
	}
	public void setTransPymtDate(String transPymtDate) {
		this.transPymtDate = transPymtDate;
	}
	public String getTransOrderProdCmp() {
		return transOrderProdCmp;
	}
	public void setTransOrderProdCmp(String transOrderProdCmp) {
		this.transOrderProdCmp = transOrderProdCmp;
	}
	public String getTransOrderProdType() {
		return transOrderProdType;
	}
	public void setTransOrderProdType(String transOrderProdType) {
		this.transOrderProdType = transOrderProdType;
	}
	public String getTransOrderProdName() {
		return transOrderProdName;
	}
	public void setTransOrderProdName(String transOrderProdName) {
		this.transOrderProdName = transOrderProdName;
	}
	public int getTransOrderProdQty() {
		return transOrderProdQty;
	}
	public void setTransOrderProdQty(int transOrderProdQty) {
		this.transOrderProdQty = transOrderProdQty;
	}
	public String getTransPymtRefId() {
		return transPymtRefId;
	}
	public void setTransPymtRefId(String transPymtRefId) {
		this.transPymtRefId = transPymtRefId;
	}
	public String getTransCustOrderRefId() {
		return transCustOrderRefId;
	}
	public void setTransCustOrderRefId(String transCustOrderRefId) {
		this.transCustOrderRefId = transCustOrderRefId;
	}
	
	
	
	
	

}
