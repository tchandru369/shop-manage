package com.merchant.management.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Column;
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
@Table(name = "customer_reward_history")
public class CustRewardHistory {

	@Id
	@GeneratedValue
	private int custId;
	@Column(name = "customer_ref_id")
	private String custRefId;
	@Column(name = "cust_reward_points")
	private String custRewardPoints;
	@Column(name = "cust_add_type")
	private String custAddType;
	@Column(name = "cust_reward_status")
	private String custRewardStatus;
	@Column(name = "cust_reward_reference")
	private String custRewardReference;
	@Column(name = "cust_reward_history_date", updatable = false)
	@CreationTimestamp
	private LocalDateTime custRewardHisDate;
	
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
	public String getCustRewardPoints() {
		return custRewardPoints;
	}
	public void setCustRewardPoints(String custRewardPoints) {
		this.custRewardPoints = custRewardPoints;
	}
	public String getCustAddType() {
		return custAddType;
	}
	public void setCustAddType(String custAddType) {
		this.custAddType = custAddType;
	}
	public String getCustRewardStatus() {
		return custRewardStatus;
	}
	public void setCustRewardStatus(String custRewardStatus) {
		this.custRewardStatus = custRewardStatus;
	}
	public String getCustRewardReference() {
		return custRewardReference;
	}
	public void setCustRewardReference(String custRewardReference) {
		this.custRewardReference = custRewardReference;
	}
	public LocalDateTime getCustRewardHisDate() {
		return custRewardHisDate;
	}
	public void setCustRewardHisDate(LocalDateTime custRewardHisDate) {
		this.custRewardHisDate = custRewardHisDate;
	}
	
	
	
	
}
