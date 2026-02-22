package com.merchant.management.dto;

import jakarta.persistence.Column;

public class MilkProductRequest {
   
	private String productOwnerRefId;
	private String companyName;
	private String productType;
	private String productName;
	private int productQuantity;
	private double productBillPrice;
	private double productShopPrice;
	private double productCustPrice;
	public String getProductOwnerRefId() {
		return productOwnerRefId;
	}
	public void setProductOwnerRefId(String productOwnerRefId) {
		this.productOwnerRefId = productOwnerRefId;
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
	
	
	
}
