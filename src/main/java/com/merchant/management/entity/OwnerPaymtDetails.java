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
@Table(name = "owner_pymt_details")
public class OwnerPaymtDetails {
  
	@Id
	@GeneratedValue
	private int pymtId;
	@Column(name = "pymt_owner_name")
	private String pymtOwnerName;
	@Column(name = "pymt_ph_number")
	private String pymtPhNumber;
	@Column(name = "pymt_upi_id")
	private String pymtUpiId;
	@Column(name = "pymt_added_on")
	private String pymtAddedOn;
	@Column(name = "pymt_mod_on")
	private String pymtModOn;
	@Column(name = "pymt_owner_email")
	private String pymtOwnerEmail;
	@Column(name = "pymt_live")
	private String pymtLive;
	public int getPymtId() {
		return pymtId;
	}
	public void setPymtId(int pymtId) {
		this.pymtId = pymtId;
	}
	public String getPymtOwnerName() {
		return pymtOwnerName;
	}
	public void setPymtOwnerName(String pymtOwnerName) {
		this.pymtOwnerName = pymtOwnerName;
	}
	public String getPymtPhNumber() {
		return pymtPhNumber;
	}
	public void setPymtPhNumber(String pymtPhNumber) {
		this.pymtPhNumber = pymtPhNumber;
	}
	public String getPymtUpiId() {
		return pymtUpiId;
	}
	public void setPymtUpiId(String pymtUpiId) {
		this.pymtUpiId = pymtUpiId;
	}
	public String getPymtAddedOn() {
		return pymtAddedOn;
	}
	public void setPymtAddedOn(String pymtAddedOn) {
		this.pymtAddedOn = pymtAddedOn;
	}
	public String getPymtModOn() {
		return pymtModOn;
	}
	public void setPymtModOn(String pymtModOn) {
		this.pymtModOn = pymtModOn;
	}
	public String getPymtOwnerEmail() {
		return pymtOwnerEmail;
	}
	public void setPymtOwnerEmail(String pymtOwnerEmail) {
		this.pymtOwnerEmail = pymtOwnerEmail;
	}
	public String getPymtLive() {
		return pymtLive;
	}
	public void setPymtLive(String pymtLive) {
		this.pymtLive = pymtLive;
	}
	
	
	
	
}
