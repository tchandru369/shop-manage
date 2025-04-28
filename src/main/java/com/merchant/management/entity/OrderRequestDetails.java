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
@Table(name = "order_request_tb")
public class OrderRequestDetails {
	
	@Id
	@GeneratedValue
	private int orderId;
	@Column(name = "order_comp_name")
	private String orderCompName;
	@Column(name = "order_product_type")
	private String orderProductType;
	@Column(name = "order_product_name")
	private String orderProductName;
	@Column(name = "order_product_qty")
	private String orderProductQty;
	@Column(name = "order_product_cust_type")
	private String orderProductCustType;
	@Column(name = "order_prod_total_amt")
	private String orderProdTotalAmt;
	@Column(name = "order_placed_date")
	private String orderPlacedDate;
	@Column(name = "order_bill_pay_flg")
	private String orderBillPayFlg;
	@Column(name = "order_bill_pay_date")
	private String orderBillPayDate;
	@Column(name = "order_cust_email_id")
	private String orderCustEmailId;
	@Column(name = "order_cust_phno")
	private String orderCustPhNo;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderCompName() {
		return orderCompName;
	}
	public void setOrderCompName(String orderCompName) {
		this.orderCompName = orderCompName;
	}
	public String getOrderProductType() {
		return orderProductType;
	}
	public void setOrderProductType(String orderProductType) {
		this.orderProductType = orderProductType;
	}
	public String getOrderProductName() {
		return orderProductName;
	}
	public void setOrderProductName(String orderProductName) {
		this.orderProductName = orderProductName;
	}
	public String getOrderProductQty() {
		return orderProductQty;
	}
	public void setOrderProductQty(String orderProductQty) {
		this.orderProductQty = orderProductQty;
	}
	public String getOrderProductCustType() {
		return orderProductCustType;
	}
	public void setOrderProductCustType(String orderProductCustType) {
		this.orderProductCustType = orderProductCustType;
	}
	public String getOrderProdTotalAmt() {
		return orderProdTotalAmt;
	}
	public void setOrderProdTotalAmt(String orderProdTotalAmt) {
		this.orderProdTotalAmt = orderProdTotalAmt;
	}
	public String getOrderPlacedDate() {
		return orderPlacedDate;
	}
	public void setOrderPlacedDate(String orderPlacedDate) {
		this.orderPlacedDate = orderPlacedDate;
	}
	public String getOrderBillPayFlg() {
		return orderBillPayFlg;
	}
	public void setOrderBillPayFlg(String orderBillPayFlg) {
		this.orderBillPayFlg = orderBillPayFlg;
	}
	public String getOrderBillPayDate() {
		return orderBillPayDate;
	}
	public void setOrderBillPayDate(String orderBillPayDate) {
		this.orderBillPayDate = orderBillPayDate;
	}
	public String getOrderCustEmailId() {
		return orderCustEmailId;
	}
	public void setOrderCustEmailId(String orderCustEmailId) {
		this.orderCustEmailId = orderCustEmailId;
	}
	public String getOrderCustPhNo() {
		return orderCustPhNo;
	}
	public void setOrderCustPhNo(String orderCustPhNo) {
		this.orderCustPhNo = orderCustPhNo;
	}
	

}
