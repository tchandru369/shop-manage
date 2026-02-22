package com.merchant.management.entity;

import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cust_overall_pymt_status")
public class CustOverallPymtStatus {

	@Id
	@GeneratedValue
	private int overallPymtId;
	private String custName;
	private String custRefId;
	private String billDate;
	private String pymtDate;
	private String pymtDateTime;
	private double pymtAmount;
	private double custPaidAmount; 
	private double pymtAmountBalance;
	private String ownerRefId;
	private String overAllOrderStatus;
	private String fullPaymentFlg;
	private String custOrderRefId;
	private String transPymtRefId;
	
	
	public String getCustOrderRefId() {
		return custOrderRefId;
	}
	public void setCustOrderRefId(String custOrderRefId) {
		this.custOrderRefId = custOrderRefId;
	}
	public String getTransPymtRefId() {
		return transPymtRefId;
	}
	public void setTransPymtRefId(String transPymtRefId) {
		this.transPymtRefId = transPymtRefId;
	}
	public int getOverallPymtId() {
		return overallPymtId;
	}
	public void setOverallPymtId(int overallPymtId) {
		this.overallPymtId = overallPymtId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getPymtDate() {
		return pymtDate;
	}
	public void setPymtDate(String pymtDate) {
		this.pymtDate = pymtDate;
	}
	public String getPymtDateTime() {
		return pymtDateTime;
	}
	public void setPymtDateTime(String pymtDateTime) {
		this.pymtDateTime = pymtDateTime;
	}
	
	public String getCustRefId() {
		return custRefId;
	}
	public void setCustRefId(String custRefId) {
		this.custRefId = custRefId;
	}
	public String getOwnerRefId() {
		return ownerRefId;
	}
	public void setOwnerRefId(String ownerRefId) {
		this.ownerRefId = ownerRefId;
	}
	public String getOverAllOrderStatus() {
		return overAllOrderStatus;
	}
	public void setOverAllOrderStatus(String overAllOrderStatus) {
		this.overAllOrderStatus = overAllOrderStatus;
	}
	public String getFullPaymentFlg() {
		return fullPaymentFlg;
	}
	public void setFullPaymentFlg(String fullPaymentFlg) {
		this.fullPaymentFlg = fullPaymentFlg;
	}
	public double getPymtAmount() {
		return pymtAmount;
	}
	public void setPymtAmount(double pymtAmount) {
		this.pymtAmount = pymtAmount;
	}
	public double getCustPaidAmount() {
		return custPaidAmount;
	}
	public void setCustPaidAmount(double custPaidAmount) {
		this.custPaidAmount = custPaidAmount;
	}
	public double getPymtAmountBalance() {
		return pymtAmountBalance;
	}
	public void setPymtAmountBalance(double pymtAmountBalance) {
		this.pymtAmountBalance = pymtAmountBalance;
	}
	
	
	
	
}
