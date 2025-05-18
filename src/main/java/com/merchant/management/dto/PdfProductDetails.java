package com.merchant.management.dto;

public class PdfProductDetails {
	
	private String productName;
	private String brandName;
	private String productType;
	private double productPrice;
	private int productQuantity;
	
	
	public PdfProductDetails() {
		
	}
	

	public PdfProductDetails(String productName, String brandName, String productType, double productPrice,
			int productQuantity) {
		super();
		this.productName = productName;
		this.brandName = brandName;
		this.productType = productType;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
	}


	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrandName() {
		return brandName;
	}


	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}


	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	public double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}


	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	
	

}
