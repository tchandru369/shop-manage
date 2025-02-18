package com.merchant.management.dto;

public class PdfProductDetails {
	
	private String productName;
	private String productActualPrice;
	private int productPrice;
	private int productQuantity;
	
	
	public PdfProductDetails() {
		
	}
	
	public PdfProductDetails(String productName, String productActualPrice, int productPrice,
			int productQuantity) {
		super();
		this.productName = productName;
		this.productActualPrice = productActualPrice;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductActualPrice() {
		return productActualPrice;
	}
	public void setProductActualPrice(String productActualPrice) {
		this.productActualPrice = productActualPrice;
	}
	public int getProductPrice() {
		return productPrice;
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
