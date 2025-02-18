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
@Table(name = "customer_details")
public class CustomerDetails {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "customer_name")
	private String customerName;
	@Column(name = "customer_unique_no")
	private String customerUniqueNo;
	@Column(name = "customer_password")
	private String CustomerPassword;
	@Column(name = "customer_email")
	private String customerEmail;
	@Column(name = "customer_phone_no")
	private String customerPhoneNo;
	@Column(name = "customer_address")
	private String customerAddress;
	@Column(name = "customer_photo")
	private String customerPhoto;
	@Column(name = "customer_creation_time")
	private String customerCreationTime;
	@Column(name = "customer_type")
	private String customerType;
	
//     public CustomerDetails() {
//    	 
//     }
//	
//	public CustomerDetails(String customerName, String customerPassword, String customerEmail, String customerPhoneNo,
//			String customerAddress, String customerPhoto,String customerUniqueNo, String customerCreationTime, String customerType) {
//		super();
//		this.customerName = customerName;
//		this.CustomerPassword = customerPassword;
//		this.customerEmail = customerEmail;
//		this.customerPhoneNo = customerPhoneNo;
//		this.customerAddress = customerAddress;
//		this.customerPhoto = customerPhoto;
//		this.customerUniqueNo =customerUniqueNo;
//		this.customerCreationTime = customerCreationTime;
//		this.customerType = customerType;
//	}
	
	public String getCustomerUniqueNo() {
		return customerUniqueNo;
	}

	public void setCustomerUniqueNo(String customerUniqueNo) {
		this.customerUniqueNo = customerUniqueNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPassword() {
		return CustomerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		CustomerPassword = customerPassword;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPhoneNo() {
		return customerPhoneNo;
	}
	public void setCustomerPhoneNo(String customerPhoneNo) {
		this.customerPhoneNo = customerPhoneNo;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerPhoto() {
		return customerPhoto;
	}
	public void setCustomerPhoto(String customerPhoto) {
		this.customerPhoto = customerPhoto;
	}
	public String getCustomerCreationTime() {
		return customerCreationTime;
	}
	public void setCustomerCreationTime(String customerCreationTime) {
		this.customerCreationTime = customerCreationTime;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
}
