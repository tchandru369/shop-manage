package com.merchant.management.entity;

import jakarta.persistence.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_details")
public class ProductDetails {
	
	@Id
	@GeneratedValue
	private long productId;
	@Column(name ="product_primary_id")
	private String productPrimaryId;
	@Column(name ="product_name")
	private String productName;
	@Column(name ="product_type")
	private String productType;
	@Column(name ="product_price")
	private String productPrice;
	@Column(name="product_cust_price")
	private String productCustomerPrice;
	@Column(name ="product_quantity")
	private String productQuantity;
	@Column(name ="product_owner")
	private String productOwner;
	@Column(name ="product_created_on") 
	private String productCreatedOn;
	@Column(name ="product_modified_on")
	private String productModified;
	
	
//	public ProductDetails(long productId,String productPrimaryId, String productName, String productType, String productPrice,String productQuantity,
//			String productOwner, String productCreatedOn, String productModified, String productCustomerPrice) {
//		super();
//		this.productId = productId;
//		this.productPrimaryId = productPrimaryId;
//		this.productName = productName;
//		this.productType = productType;
//		this.productPrice = productPrice;
//		this.productQuantity = productQuantity;	
//		this.productOwner = productOwner;
//		this.productCreatedOn = productCreatedOn;
//		this.productModified = productModified;
//		this.productCustomerPrice = productCustomerPrice;
//	}
//
//	public ProductDetails() {
//		
//	}
	
	public String getProductCustomerPrice() {
		return productCustomerPrice;
	}

	public void setProductCustomerPrice(String productCustomerPrice) {
		this.productCustomerPrice = productCustomerPrice;
	}

	public String getProductPrimaryId() {
		return productPrimaryId;
	}

	public void setProductPrimaryId(String productPrimaryId) {
		this.productPrimaryId = productPrimaryId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductOwner() {
		return productOwner;
	}
	public void setProductOwner(String productOwner) {
		this.productOwner = productOwner;
	}
	public String getProductCreatedOn() {
		return productCreatedOn;
	}
	public void setProductCreatedOn(String productCreatedOn) {
		this.productCreatedOn = productCreatedOn;
	}
	public String getProductModified() {
		return productModified;
	}
	public void setProductModified(String productModified) {
		this.productModified = productModified;
	}
	
	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	

}
