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
@Table(name = "final_order_details_tb")
public class FinalOrderDetailsDb {

	@Id
	@GeneratedValue
	private int foDetailsId;
	@Column(name = "fo_owner_name")
	private String foOwnerName;
	@Column(name = "fo_owner_email_id")
	private String foOwnerEmailId;
	@Column(name = "fo_owner_phone_no")
	private String foOwnerPhoneNo;
	@Column(name = "fo_owner_product_type")
	private String foOwnerProductType;
	@Column(name = "fo_owner_product_name")
	private String foOwnerProductName;
	@Column(name = "fo_owner_product_qty")
	private int foOwmerProductQty;
	@Column(name = "fo_owner_bill_price")
	private double foOwnerBillPrice;
	@Column(name = "fo_owner_bill_status")
	private String foOwnerBillStatus;
	public int getFoDetailsId() {
		return foDetailsId;
	}
	public void setFoDetailsId(int foDetailsId) {
		this.foDetailsId = foDetailsId;
	}
	public String getFoOwnerName() {
		return foOwnerName;
	}
	public void setFoOwnerName(String foOwnerName) {
		this.foOwnerName = foOwnerName;
	}
	public String getFoOwnerEmailId() {
		return foOwnerEmailId;
	}
	public void setFoOwnerEmailId(String foOwnerEmailId) {
		this.foOwnerEmailId = foOwnerEmailId;
	}
	public String getFoOwnerPhoneNo() {
		return foOwnerPhoneNo;
	}
	public void setFoOwnerPhoneNo(String foOwnerPhoneNo) {
		this.foOwnerPhoneNo = foOwnerPhoneNo;
	}
	public String getFoOwnerProductType() {
		return foOwnerProductType;
	}
	public void setFoOwnerProductType(String foOwnerProductType) {
		this.foOwnerProductType = foOwnerProductType;
	}
	public String getFoOwnerProductName() {
		return foOwnerProductName;
	}
	public void setFoOwnerProductName(String foOwnerProductName) {
		this.foOwnerProductName = foOwnerProductName;
	}
	public int getFoOwmerProductQty() {
		return foOwmerProductQty;
	}
	public void setFoOwmerProductQty(int foOwmerProductQty) {
		this.foOwmerProductQty = foOwmerProductQty;
	}
	public double getFoOwnerBillPrice() {
		return foOwnerBillPrice;
	}
	public void setFoOwnerBillPrice(double foOwnerBillPrice) {
		this.foOwnerBillPrice = foOwnerBillPrice;
	}
	public String getFoOwnerBillStatus() {
		return foOwnerBillStatus;
	}
	public void setFoOwnerBillStatus(String foOwnerBillStatus) {
		this.foOwnerBillStatus = foOwnerBillStatus;
	}
	
	
	
	
}
