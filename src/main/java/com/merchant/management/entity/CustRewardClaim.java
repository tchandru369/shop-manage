package com.merchant.management.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "customer_add_details")
public class CustRewardClaim {
	
	@Id
	@GeneratedValue
	private int custId;
	@Column(name = "customer_ref_id")
	private String custRefId;
	@Column(name = "cust_claim_points")
	private int custClaimPoints;
	@Column(name = "cust_claim_amount")
	private String custClaimAmount;
	@Column(name = "cust_claim_status")
	private String custClaimStatus;
	@Column(name = "cust_claim_req_date", updatable = false)
	@CreationTimestamp
	private LocalDateTime custClaimRequestedDate;
	
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustRefId() {
		return custRefId;
	}
	public void setCustRefId(String custRefId) {
		this.custRefId = custRefId;
	}
	public int getCustClaimPoints() {
		return custClaimPoints;
	}
	public void setCustClaimPoints(int custClaimPoints) {
		this.custClaimPoints = custClaimPoints;
	}
	public String getCustClaimAmount() {
		return custClaimAmount;
	}
	public void setCustClaimAmount(String custClaimAmount) {
		this.custClaimAmount = custClaimAmount;
	}
	public String getCustClaimStatus() {
		return custClaimStatus;
	}
	public void setCustClaimStatus(String custClaimStatus) {
		this.custClaimStatus = custClaimStatus;
	}
	public LocalDateTime getCustClaimRequestedDate() {
		return custClaimRequestedDate;
	}
	public void setCustClaimRequestedDate(LocalDateTime custClaimRequestedDate) {
		this.custClaimRequestedDate = custClaimRequestedDate;
	}
	
	
	
	
	

}
