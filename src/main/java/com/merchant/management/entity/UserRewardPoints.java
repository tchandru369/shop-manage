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
@Table(name = "user_reward_points_tb")
public class UserRewardPoints {

	@Id
	@GeneratedValue
	private int userRewardId;
	@Column(name = "user_reward_ref_id")
	private String userRewardRefId;
	@Column(name = "user_reward_email_id")
	private String userRewardEmailId;
	@Column(name = "user_reward_type")
	private String userRewardType;
	@Column(name = "user_reward_points")
	private int userRewardPoints;
	@Column(name = "user_reward_active")
	private String userRewardActive;
	@Column(name = "user_reward_last_update_time")
	private String userRewardLastUpdateTime;
	
	
	public int getUserRewardId() {
		return userRewardId;
	}
	public void setUserRewardId(int userRewardId) {
		this.userRewardId = userRewardId;
	}
	public String getUserRewardRefId() {
		return userRewardRefId;
	}
	public void setUserRewardRefId(String userRewardRefId) {
		this.userRewardRefId = userRewardRefId;
	}
	public String getUserRewardEmailId() {
		return userRewardEmailId;
	}
	public void setUserRewardEmailId(String userRewardEmailId) {
		this.userRewardEmailId = userRewardEmailId;
	}
	public String getUserRewardType() {
		return userRewardType;
	}
	public void setUserRewardType(String userRewardType) {
		this.userRewardType = userRewardType;
	}
	public int getUserRewardPoints() {
		return userRewardPoints;
	}
	public void setUserRewardPoints(int userRewardPoints) {
		this.userRewardPoints = userRewardPoints;
	}
	public String getUserRewardActive() {
		return userRewardActive;
	}
	public void setUserRewardActive(String userRewardActive) {
		this.userRewardActive = userRewardActive;
	}
	public String getUserRewardLastUpdateTime() {
		return userRewardLastUpdateTime;
	}
	public void setUserRewardLastUpdateTime(String userRewardLastUpdateTime) {
		this.userRewardLastUpdateTime = userRewardLastUpdateTime;
	}
	
	
	
}
