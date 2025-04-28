package com.merchant.management.entity;

public class ComProdDtls {

	private String comName;
	private String prodType;
	private String prodName;
	
	public ComProdDtls(String comName, String prodType, String prodName) {
		super();
		this.comName = comName;
		this.prodType = prodType;
		this.prodName = prodName;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
}
