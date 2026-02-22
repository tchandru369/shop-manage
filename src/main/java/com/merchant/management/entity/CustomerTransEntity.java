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
@Table(name = "cust_trans_entity")
public class CustomerTransEntity {
	
	@Id
	@GeneratedValue
	private int transId;
	@Column(name="trans_payee_upi_id")
	private String payeeUpiId;
	@Column(name="trans_payer_upi_id")
	private String payerUpiId;
	@Column(name="trans_payer_phone_no")
	private String payerPhoneNo;
	@Column(name="trans_payee_phone_no")
	private String payeePhoneNo;
	@Column(name="payee_note")
	private String payeeNote;
	@Column(name="trans_cust_ref_id")
	private String transCustRefId;
	@Column(name="trans_own_ref_id")
	private String transOwnerRefId;
	@Column(name="trans_act_amt")
	private double transActAmt;
	@Column(name="trans_pymt_amt")
	private double transPymtAmt;
	@Column(name="trans_blns_amt")
	private double transBlnAmt;
	@Column(name="trans_pymt_date")
	private String transPymtDate;
	@Column(name="trans_pymt_time")
	private String transPymtTime;
	@Column(name="trans_pymt_ref_id")
	private String transPymtRefId;
	@Column(name="trans_cust_order_id")
	private String transCustOrderId;
	@Column(name="trans_pymt_order_status")
	private String transPymtOrderStatus;
	
	
	
	
	
	public String getTransCustOrderId() {
		return transCustOrderId;
	}
	public void setTransCustOrderId(String transCustOrderId) {
		this.transCustOrderId = transCustOrderId;
	}
	public String getPayeeUpiId() {
		return payeeUpiId;
	}
	public void setPayeeUpiId(String payeeUpiId) {
		this.payeeUpiId = payeeUpiId;
	}
	public String getPayerUpiId() {
		return payerUpiId;
	}
	public void setPayerUpiId(String payerUpiId) {
		this.payerUpiId = payerUpiId;
	}
	public String getPayeeNote() {
		return payeeNote;
	}
	public void setPayeeNote(String payeeNote) {
		this.payeeNote = payeeNote;
	}
	
	public String getTransCustRefId() {
		return transCustRefId;
	}
	public void setTransCustRefId(String transCustRefId) {
		this.transCustRefId = transCustRefId;
	}
	public String getTransOwnerRefId() {
		return transOwnerRefId;
	}
	public void setTransOwnerRefId(String transOwnerRefId) {
		this.transOwnerRefId = transOwnerRefId;
	}
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}
	public double getTransActAmt() {
		return transActAmt;
	}
	public void setTransActAmt(double transActAmt) {
		this.transActAmt = transActAmt;
	}
	public double getTransPymtAmt() {
		return transPymtAmt;
	}
	public void setTransPymtAmt(double transPymtAmt) {
		this.transPymtAmt = transPymtAmt;
	}
	public double getTransBlnAmt() {
		return transBlnAmt;
	}
	public void setTransBlnAmt(double transBlnAmt) {
		this.transBlnAmt = transBlnAmt;
	}
	public String getTransPymtDate() {
		return transPymtDate;
	}
	public void setTransPymtDate(String transPymtDate) {
		this.transPymtDate = transPymtDate;
	}
	public String getTransPymtTime() {
		return transPymtTime;
	}
	public void setTransPymtTime(String transPymtTime) {
		this.transPymtTime = transPymtTime;
	}
	public String getTransPymtRefId() {
		return transPymtRefId;
	}
	public void setTransPymtRefId(String transPymtRefId) {
		this.transPymtRefId = transPymtRefId;
	}
	public String getPayerPhoneNo() {
		return payerPhoneNo;
	}
	public void setPayerPhoneNo(String payerPhoneNo) {
		this.payerPhoneNo = payerPhoneNo;
	}
	public String getPayeePhoneNo() {
		return payeePhoneNo;
	}
	public void setPayeePhoneNo(String payeePhoneNo) {
		this.payeePhoneNo = payeePhoneNo;
	}
	public String getTransPymtOrderStatus() {
		return transPymtOrderStatus;
	}
	public void setTransPymtOrderStatus(String transPymtOrderStatus) {
		this.transPymtOrderStatus = transPymtOrderStatus;
	}
	
	
	
	
	
	
	

}
