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
@Table(name = "customer_add_reward_details")
public class CustAddRewardDetails {
	

	@Id
	@GeneratedValue
	private int custId;
	@Column(name = "customer_ref_id")
	private String custRefId;
	@Column(name = "cust_mobile_no")
	private String custMobileNo;
	@Column(name = "cust_reward_points")
	private String custRewardPoints;
	@Column(name = "cust_add_watched_today")
	private String custAddWatchedToday;
	@Column(name = "cust_reward_created_date", updatable = false)
	@CreationTimestamp
	private LocalDateTime custRewardCreatedDate;
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
	public String getCustMobileNo() {
		return custMobileNo;
	}
	public void setCustMobileNo(String custMobileNo) {
		this.custMobileNo = custMobileNo;
	}
	public String getCustRewardPoints() {
		return custRewardPoints;
	}
	public void setCustRewardPoints(String custRewardPoints) {
		this.custRewardPoints = custRewardPoints;
	}
	public String getCustAddWatchedToday() {
		return custAddWatchedToday;
	}
	public void setCustAddWatchedToday(String custAddWatchedToday) {
		this.custAddWatchedToday = custAddWatchedToday;
	}
	public LocalDateTime getCustRewardCreatedDate() {
		return custRewardCreatedDate;
	}
	public void setCustRewardCreatedDate(LocalDateTime custRewardCreatedDate) {
		this.custRewardCreatedDate = custRewardCreatedDate;
	}
	
	
	
	
	

}
