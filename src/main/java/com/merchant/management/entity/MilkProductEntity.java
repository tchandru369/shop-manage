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
@Table(name = "milk_products_tb")
public class MilkProductEntity {
	@Id
	@GeneratedValue
	private int product_id;
	@Column(name = "product_owner")
	private String productOwner;
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "product_type")
	private String productType;
	@Column(name = "product_name")
	private String productName;
	@Column(name = "product_quantity")
	private int productQuantity;
	@Column(name = "product_bill_price")
	private double productBillPrice;
	@Column(name = "product_shop_price")
	private double productShopPrice;
	@Column(name = "product_cust_price")
	private double productCustPrice;
	@Column(name = "product_live")
	private String productLive;
	@Column(name = "product_created_date")
	private String productCreatedDate;
	
	public String getProductCreatedDate() {
		return productCreatedDate;
	}
	public void setProductCreatedDate(String productCreatedDate) {
		this.productCreatedDate = productCreatedDate;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public double getProductBillPrice() {
		return productBillPrice;
	}
	public void setProductBillPrice(double productBillPrice) {
		this.productBillPrice = productBillPrice;
	}
	public double getProductShopPrice() {
		return productShopPrice;
	}
	public void setProductShopPrice(double productShopPrice) {
		this.productShopPrice = productShopPrice;
	}
	public double getProductCustPrice() {
		return productCustPrice;
	}
	public void setProductCustPrice(double productCustPrice) {
		this.productCustPrice = productCustPrice;
	}
	public String getProductLive() {
		return productLive;
	}
	public void setProductLive(String productLive) {
		this.productLive = productLive;
	}
	public String getProductOwner() {
		return productOwner;
	}
	public void setProductOwner(String productOwner) {
		this.productOwner = productOwner;
	}
}
