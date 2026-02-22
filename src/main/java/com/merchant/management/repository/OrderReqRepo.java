package com.merchant.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.OrderRequestDetails;

@EnableJpaRepositories
public interface OrderReqRepo extends JpaRepository<OrderRequestDetails, Integer>{

	@Query(value = "SELECT * FROM order_request_details ea WHERE ea.order_request_status ='OPA' AND ea.order_owner_ref_id =:ownerRefId", nativeQuery = true)
	List<OrderRequestDetails> getOrderRequestCreated(String ownerRefId); 
	
	@Query(value = "SELECT * FROM order_request_details ea WHERE ea.order_request_status ='BP' AND ea.order_owner_ref_id =:ownerRefId", nativeQuery = true)
	List<OrderRequestDetails> getProcessedOrders(String ownerRefId);
	
	@Query(value = "SELECT * FROM order_request_details ea WHERE ea.order_request_status ='BSV' AND ea.order_owner_ref_id =:ownerRefId", nativeQuery = true)
	List<OrderRequestDetails> getCustPymtConfirmOrders(String ownerRefId);
	
	@Query(value = "SELECT * FROM order_request_details ea WHERE ea.order_request_status ='BP' AND ea.order_owner_ref_id =:ownerRefId AND order_cust_ref_id=:custRefId", nativeQuery = true)
	List<OrderRequestDetails> getCustProcessedOrders(String ownerRefId,String custRefId);
	
	@Query(value = "SELECT COUNT(*) FROM order_request_details WHERE order_cust_ref_id =:custRefId AND\r\n"
			+ "	order_cust_phno =:custPhNo AND order_placed_date =:orderDate AND order_product_cust_type =:custType\r\n"
			+ "	AND order_owner_ref_id =:ownerRefId AND order_request_status = 'OPA';", nativeQuery = true)
	int getCustOrderCount(String custRefId, String custPhNo, String orderDate, String custType,String ownerRefId);
	
	@Query(value = "SELECT COUNT(*) FROM order_request_details WHERE order_cust_ref_id =:custRefId AND\r\n"
			+ " order_placed_date =:orderDate AND order_ref_id =:orderRefId AND order_product_cust_type =:custType\r\n"
			+ "	AND order_owner_ref_id =:ownerRefId AND order_request_status = 'BP';", nativeQuery = true)
	int getProcOrderCount(String custRefId, String orderDate, String custType,String ownerRefId, String orderRefId);
	
	@Query(value = "SELECT order_prod_total_amt FROM order_request_details WHERE order_cust_ref_id =:custRefId AND order_placed_date =:placedDate AND order_owner_ref_id=:ownerRefId AND order_ref_id=:orderRefId", nativeQuery = true)
    double getOrderplcdTotalAmt(String custRefId,String placedDate,String ownerRefId,String orderRefId);
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "DELETE FROM order_request_details WHERE order_cust_ref_id =:custRefId AND order_placed_date =:orderDate AND order_owner_ref_id =:ownerRefId AND order_product_cust_type =:custType AND order_request_status ='OPA' AND order_ref_id=:orderRefId", nativeQuery = true)
	int deleteOrderReqTable(@Param("custRefId") String custRefId,
            @Param("orderDate") String orderDate,
            @Param("custType") String custType,
            @Param("ownerRefId") String ownerRefId,@Param("orderRefId") String orderRefId);
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "DELETE FROM order_request_details WHERE order_cust_ref_id =:custRefId AND order_ref_id=:orderRefId AND order_placed_date =:orderDate AND order_owner_ref_id =:ownerRefId AND order_product_cust_type =:custType AND order_request_status ='BP'", nativeQuery = true)
	int deleteProcOrderReqTable(@Param("custRefId") String custRefId,
            @Param("orderDate") String orderDate,
            @Param("custType") String custType,
            @Param("ownerRefId") String ownerRefId,@Param("orderRefId") String orderRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_request_status ='BP'  WHERE order_owner_ref_id =:ownerRefId AND order_product_cust_type =:custType AND order_placed_date =:orderedDate AND order_cust_ref_id =:custRefId AND order_ref_id=:orderRefId AND order_request_status ='OPA'", nativeQuery = true)
	void updateOrderStatus(String ownerRefId,String custType,String orderedDate,String custRefId,String orderRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_balance_flg =:blnceFlg, order_bill_pay_date =:billDate,\r\n"
			+ "order_request_status ='BS',order_pymt_ref_id =:pymtRefId WHERE order_cust_ref_id =:custRefId AND \r\n"
			+ "order_placed_date =:orderDate AND order_product_cust_type =:custType AND order_owner_ref_id =:ownerRefId AND order_request_status ='BP'", nativeQuery = true)
	void updateordStsBill(String blnceFlg,String billDate,String custRefId,String orderDate,String custType,String ownerRefId,String pymtRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_balance_flg =:blnceFlg, order_bill_pay_date =:billDate,\r\n"
			+ "order_request_status ='BS' WHERE order_cust_ref_id =:custRefId AND order_ref_id =:orderRefId \r\n"
			+ "AND order_placed_date =:orderDate AND order_owner_ref_id =:ownerRefId AND order_request_status ='BSV'", nativeQuery = true)
	void updatecustPymntVerBill(String blnceFlg,String billDate,String custRefId,String orderDate,String orderRefId,String ownerRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_balance_flg =:blnceFlg, order_bill_pay_date =:billDate,\r\n"
			+ "order_request_status ='BP' WHERE order_cust_ref_id =:custRefId AND \r\n"
			+ "order_placed_date =:orderDate AND order_ref_id =:orderRefId AND order_product_cust_type =:custType AND order_owner_ref_id =:ownerRefId AND order_request_status ='BSV'", nativeQuery = true)
	void updtOwnerCustNotPaidConfirm(String blnceFlg,String billDate,String custRefId,String orderDate,String custType,String ownerRefId,String orderRefId);

	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_balance_flg =:blnceFlg,order_pymt_ref_id =:pymtRefId, order_bill_pay_date =:billDate,\r\n"
			+ "order_request_status ='BSV' WHERE order_cust_ref_id =:custRefId AND \r\n"
			+ "order_placed_date =:orderDate AND order_ref_id =:orderRefId AND order_owner_ref_id =:ownerRefId AND order_request_status ='BP'", nativeQuery = true)
	void updateCustConfPymtordStsBill(String blnceFlg,String billDate,String custRefId,String orderDate,String ownerRefId,String orderRefId,String pymtRefId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_balance_flg =:blnceFlg, order_bill_pay_date =:billDate,\r\n"
			+ "order_request_status ='BS' WHERE order_cust_ref_id =:custRefId AND \r\n"
			+ "order_placed_date =:orderDate AND order_product_cust_type =:custType AND order_owner_ref_id =:ownerRefId AND order_request_status ='BSV'", nativeQuery = true)
	void updateOwnerConfPymtCustStsBill(String blnceFlg,String billDate,String custRefId,String orderDate,String custType,String ownerRefId);

	
	
	@Query(value = "SELECT * FROM order_request_details WHERE order_cust_ref_id =:custRefId AND order_owner_ref_id =:ownerRefId", nativeQuery = true)
	List<OrderRequestDetails> getCustOrderTranDetails(String ownerRefId,String custRefId);
}
