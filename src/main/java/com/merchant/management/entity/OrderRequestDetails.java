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
@Table(name = "order_request_details")
public class OrderRequestDetails {
	
	@Id
	@GeneratedValue
	private int orderId;
	@Column(name = "order_product_cust_type")
	private String orderProductCustType;
	@Column(name = "order_prod_cust_name")
	private String orderProductCustName;
	@Column(name = "order_owner_ref_id")
	private String orderOwnerRefId;
	@Column(name = "order_cust_ref_id")
	private String orderCustRefId;
	@Column(name = "order_prod_total_amt")
	private double orderProdTotalAmt;
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
	@Column(name = "order_ref_id")
	private String orderRefId;
	@Column(name = "order_pymt_ref_id")
	private String orderPymtRefId;
	@Column(name = "order_request_status")
	private String orderRequestStatus;
	@Column(name = "order_balance_flg")
	private String orderBalanceFlg;
	
	
	
	public String getOrderRefId() {
		return orderRefId;
	}
	public void setOrderRefId(String orderRefId) {
		this.orderRefId = orderRefId;
	}
	public String getorderPymtRefId() {
		return orderPymtRefId;
	}
	public void setorderPymtRefId(String orderPymtRefId) {
		this.orderPymtRefId = orderPymtRefId;
	}
	public String getOrderOwnerRefId() {
		return orderOwnerRefId;
	}
	public void setOrderOwnerRefId(String orderOwnerRefId) {
		this.orderOwnerRefId = orderOwnerRefId;
	}
	public String getOrderProductCustName() {
		return orderProductCustName;
	}
	public void setOrderProductCustName(String orderProductCustName) {
		this.orderProductCustName = orderProductCustName;
	}
	public String getOrderBalanceFlg() {
		return orderBalanceFlg;
	}
	public void setOrderBalanceFlg(String orderBalanceFlg) {
		this.orderBalanceFlg = orderBalanceFlg;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderRequestStatus() {
		return orderRequestStatus;
	}
	public void setOrderRequestStatus(String orderRequestStatus) {
		this.orderRequestStatus = orderRequestStatus;
	}
	public String getOrderProductCustType() {
		return orderProductCustType;
	}
	public void setOrderProductCustType(String orderProductCustType) {
		this.orderProductCustType = orderProductCustType;
	}
	public double getOrderProdTotalAmt() {
		return orderProdTotalAmt;
	}
	public void setOrderProdTotalAmt(double orderProdTotalAmt) {
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
	public String getOrderCustRefId() {
		return orderCustRefId;
	}
	public void setOrderCustRefId(String orderCustRefId) {
		this.orderCustRefId = orderCustRefId;
	}
	public String getOrderPymtRefId() {
		return orderPymtRefId;
	}
	public void setOrderPymtRefId(String orderPymtRefId) {
		this.orderPymtRefId = orderPymtRefId;
	}
	
	

}
