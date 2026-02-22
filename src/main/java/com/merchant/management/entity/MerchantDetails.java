package com.merchant.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.merchant.management.entity.Role;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "merchant_details")
public class MerchantDetails implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long merchantId;
	@Column(unique = true)
    private String merchantRefId;
	@Column(name = "merchant_name")
	private String merchantUserName;
	@Column(name = "merchant_password")
	private String merchantPassword;
	@Column(name = "merchant_email")
	private String merchantEmail;
	@Column(name = "merchant_phone")
	private String merchantPhoneNumber;
	@Column(name = "merchant_address")
	private String merchantAddress;
	@Column(name = "merchant_photo")
	private String merchantPhoto;
	@Column(name = "merchantSignUp_time")
	private String merchantSignUpTime;
	@Column(name = "merchant_userType")
	private String merchantUserType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role",columnDefinition = "VARCHAR(255)")
	private Role role;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
//	public MerchantDetails() {
//	}
//	public MerchantDetails(String merchantUserName, String merchantPassword, String merchantEmail, String merchantPhoneNumber,
//			String merchantAddress, String merchantPhoto, String merchantUserType) {
//		super();
//		this.merchantUserName = merchantUserName;
//		this.merchantPassword = merchantPassword;
//		this.merchantEmail = merchantEmail;
//		this.merchantPhoneNumber = merchantPhoneNumber;
//		this.merchantAddress = merchantAddress;
//		this.merchantPhoto = merchantPhoto;
//		this.merchantUserType = merchantUserType;
//	}
	
	
	public String getMerchantUserType() {
		return merchantUserType;
	}
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantRefId() {
		return merchantRefId;
	}
	public void setMerchantRefId(String merchantRefId) {
		this.merchantRefId = merchantRefId;
	}
	public String getMerchantUserName() {
		return merchantUserName;
	}
	public void setMerchantUserName(String merchantUserName) {
		this.merchantUserName = merchantUserName;
	}
	public String getMerchantPhoneNumber() {
		return merchantPhoneNumber;
	}
	public void setMerchantPhoneNumber(String merchantPhoneNumber) {
		this.merchantPhoneNumber = merchantPhoneNumber;
	}
	public void setMerchantUserType(String merchantUserType) {
		this.merchantUserType = merchantUserType;
	}
	public String getMerchantPassword() {
		return merchantPassword;
	}
	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}
	public String getMerchantEmail() {
		return merchantEmail;
	}
	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}
	public String getMerchantAddress() {
		return merchantAddress;
	}
	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}
	public String getMerchantPhoto() {
		return merchantPhoto;
	}
	public void setMerchantPhoto(String merchantPhoto) {
		this.merchantPhoto = merchantPhoto;
	}
	
	public String getMerchantSignUpTime() {
		return merchantSignUpTime;
	}
	public void setMerchantSignUpTime(String merchantSignUpTime) {
		this.merchantSignUpTime = merchantSignUpTime;
	}
	/*
	 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
	 * return List.of(); }
	 * 
	 * public String getPassword() { return password; }
	 * 
	 * @Override public String getUsername() { return email; }
	 * 
	 * @Override public boolean isAccountNonExpired() { return true; }
	 * 
	 * @Override public boolean isAccountNonLocked() { return true; }
	 * 
	 * @Override public boolean isCredentialsNonExpired() { return true; }
	 * 
	 * 
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return merchantPassword;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return merchantEmail;
	}
	
	@Override public boolean isEnabled() { return true; }
	
	@Override public boolean isAccountNonExpired() { return true; }
	
	 @Override public boolean isAccountNonLocked() { return true; }
	 
	 
	 @Override public boolean isCredentialsNonExpired() { return true; }
	
}
