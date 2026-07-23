package com.merchant.management.entity;

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
@Table(name = "customer_reward_config")
public class CustRewardConfig {
	
	@Id
	@GeneratedValue
	private int custId;
	@Column(name = "customer_ref_id")
	private String custRefId;
	@Column(name = "cust_points_per_add")
	private int custPointsPerAdd;
	@Column(name = "cust_daily_limit")
	private int custDailyLimit;
	@Column(name = "cust_cool_down_seconds")
	private int custCoolDownSeconds;
	@Column(name = "cust_min_claim_points")
	private int custMinClaimPoints;
	@Column(name = "cust_point_value")
	private double custPointValue;
	
	
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
	public int getCustPointsPerAdd() {
		return custPointsPerAdd;
	}
	public void setCustPointsPerAdd(int custPointsPerAdd) {
		this.custPointsPerAdd = custPointsPerAdd;
	}
	public int getCustDailyLimit() {
		return custDailyLimit;
	}
	public void setCustDailyLimit(int custDailyLimit) {
		this.custDailyLimit = custDailyLimit;
	}
	public int getCustCoolDownSeconds() {
		return custCoolDownSeconds;
	}
	public void setCustCoolDownSeconds(int custCoolDownSeconds) {
		this.custCoolDownSeconds = custCoolDownSeconds;
	}
	public int getCustMinClaimPoints() {
		return custMinClaimPoints;
	}
	public void setCustMinClaimPoints(int custMinClaimPoints) {
		this.custMinClaimPoints = custMinClaimPoints;
	}
	public double getCustPointValue() {
		return custPointValue;
	}
	public void setCustPointValue(double custPointValue) {
		this.custPointValue = custPointValue;
	}
	
	
	

}
