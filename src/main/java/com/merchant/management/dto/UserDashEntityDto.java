package com.merchant.management.dto;

import java.util.List;

import com.merchant.management.entity.MilkProductEntity;

public class UserDashEntityDto {
	
	private int customerCount;
	private int custOrdReqCount;
	private int custPymtReqCount;
	private double totalClctWeek;
	private double totalClctMonth;
	private double totalClctNow;
	private double totalOrderWeek;
	private double totalOrderMonth;
	private double totalOrderNow;
	private List<MilkProductEntity> milkProdList;
	
	public UserDashEntityDto() {
		
	}
	
	
	public UserDashEntityDto(int customerCount, int custOrdReqCount, int custPymtReqCount, double totalClctWeek,
			double totalClctMonth, double totalClctYear, double totalOrderWeek, double totalOrderMonth,
			double totalOrderYear, List<MilkProductEntity> milkProdList) {
		super();
		this.customerCount = customerCount;
		this.custOrdReqCount = custOrdReqCount;
		this.custPymtReqCount = custPymtReqCount;
		this.totalClctWeek = totalClctWeek;
		this.totalClctMonth = totalClctMonth;
		this.totalClctNow = totalClctNow;
		this.totalOrderWeek = totalOrderWeek;
		this.totalOrderMonth = totalOrderMonth;
		this.totalOrderNow = totalOrderNow;
		this.milkProdList = milkProdList;
	}
	public int getCustomerCount() {
		return customerCount;
	}
	public void setCustomerCount(int customerCount) {
		this.customerCount = customerCount;
	}
	public int getCustOrdReqCount() {
		return custOrdReqCount;
	}
	public void setCustOrdReqCount(int custOrdReqCount) {
		this.custOrdReqCount = custOrdReqCount;
	}
	public int getCustPymtReqCount() {
		return custPymtReqCount;
	}
	public void setCustPymtReqCount(int custPymtReqCount) {
		this.custPymtReqCount = custPymtReqCount;
	}
	public double getTotalClctWeek() {
		return totalClctWeek;
	}
	public void setTotalClctWeek(double totalClctWeek) {
		this.totalClctWeek = totalClctWeek;
	}
	public double getTotalClctMonth() {
		return totalClctMonth;
	}
	public void setTotalClctMonth(double totalClctMonth) {
		this.totalClctMonth = totalClctMonth;
	}
	public double getTotalClctNow() {
		return totalClctNow;
	}
	public void setTotalClctNow(double totalClctNow) {
		this.totalClctNow = totalClctNow;
	}
	public double getTotalOrderWeek() {
		return totalOrderWeek;
	}
	public void setTotalOrderWeek(double totalOrderWeek) {
		this.totalOrderWeek = totalOrderWeek;
	}
	public double getTotalOrderMonth() {
		return totalOrderMonth;
	}
	public void setTotalOrderMonth(double totalOrderMonth) {
		this.totalOrderMonth = totalOrderMonth;
	}
	public double getTotalOrderNow() {
		return totalOrderNow;
	}
	public void setTotalOrderNow(double totalOrderNow) {
		this.totalOrderNow = totalOrderNow;
	}
	public List<MilkProductEntity> getMilkProdList() {
		return milkProdList;
	}
	public void setMilkProdList(List<MilkProductEntity> milkProdList) {
		this.milkProdList = milkProdList;
	}
	
	
	

}
