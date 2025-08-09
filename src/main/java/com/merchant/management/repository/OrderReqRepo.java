package com.merchant.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.OrderRequestDetails;

public interface OrderReqRepo extends JpaRepository<OrderRequestDetails, Integer>{

	@Query(value = "SELECT * FROM order_request_details ea WHERE ea.order_request_status ='RA' AND ea.order_prod_owner_name =:ownerEmail", nativeQuery = true)
	List<OrderRequestDetails> getOrderRequestCreated(String ownerEmail); 
	
	@Query(value = "SELECT * FROM order_request_details ea WHERE ea.order_request_status ='BP' AND ea.order_prod_owner_name =:ownerEmail", nativeQuery = true)
	List<OrderRequestDetails> getProcessedOrders(String ownerEmail);
	
	@Query(value = "SELECT * FROM order_request_details ea WHERE ea.order_request_status ='BP' AND ea.order_prod_owner_name =:ownerEmail AND order_cust_email_id=:custEmail", nativeQuery = true)
	List<OrderRequestDetails> getCustProcessedOrders(String ownerEmail,String custEmail);
	
	@Query(value = "SELECT COUNT(*) FROM order_request_details WHERE order_cust_email_id =:custEmail AND\r\n"
			+ "	order_cust_phno =:custPhNo AND order_placed_date =:orderDate AND order_product_cust_type =:custType\r\n"
			+ "	AND order_prod_owner_name =:ownerEmail AND order_request_status = 'RA';", nativeQuery = true)
	int getCustOrderCount(String custEmail, String custPhNo, String orderDate, String custType,String ownerEmail);
	
	@Query(value = "SELECT COUNT(*) FROM order_request_details WHERE order_cust_email_id =:custEmail AND\r\n"
			+ "	order_cust_phno =:custPhNo AND order_placed_date =:orderDate AND order_product_cust_type =:custType\r\n"
			+ "	AND order_prod_owner_name =:ownerEmail AND order_request_status = 'BP';", nativeQuery = true)
	int getProcOrderCount(String custEmail, String custPhNo, String orderDate, String custType,String ownerEmail);
    
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "DELETE FROM order_request_details WHERE order_cust_email_id =:custEmail AND order_cust_phno =:custPhNo AND order_placed_date =:orderDate AND order_prod_owner_name =:ownerEmail AND order_product_cust_type =:custType AND order_request_status ='RA'", nativeQuery = true)
	int deleteOrderReqTable(@Param("custEmail") String custEmail,
            @Param("custPhNo") String custPhNo,
            @Param("orderDate") String orderDate,
            @Param("custType") String custType,
            @Param("ownerEmail") String ownerEmail);
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "DELETE FROM order_request_details WHERE order_cust_email_id =:custEmail AND order_cust_phno =:custPhNo AND order_placed_date =:orderDate AND order_prod_owner_name =:ownerEmail AND order_product_cust_type =:custType AND order_request_status ='BP'", nativeQuery = true)
	int deleteProcOrderReqTable(@Param("custEmail") String custEmail,
            @Param("custPhNo") String custPhNo,
            @Param("orderDate") String orderDate,
            @Param("custType") String custType,
            @Param("ownerEmail") String ownerEmail);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_request_status ='BP'  WHERE order_prod_owner_name =:ownerEmail \r\n"
			+ "AND order_product_cust_type =:custType AND order_placed_date =:orderedDate AND order_cust_phno =:custPhNo\r\n"
			+ "AND order_cust_email_id =:custEmail AND order_request_status ='RA'", nativeQuery = true)
	void updateOrderStatus(String ownerEmail,String custType,String orderedDate, String custPhNo,String custEmail);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE order_request_details SET order_balance_flg =:blnceFlg, order_bill_pay_date =:billDate,\r\n"
			+ "order_request_status ='BS' WHERE order_cust_email_id =:custEmail AND order_cust_phno =:custPhoneNo AND \r\n"
			+ "order_placed_date =:orderDate AND order_product_cust_type =:custType AND order_prod_owner_name =:ownerName AND order_request_status ='BP'", nativeQuery = true)
	void updateordStsBill(String blnceFlg,String billDate,String custEmail, String custPhoneNo,String orderDate,String custType,String ownerName);
	
	@Query(value = "SELECT * FROM order_request_details WHERE order_cust_email_id =:custEmail AND order_prod_owner_name =:ownerEmail", nativeQuery = true)
	List<OrderRequestDetails> getCustOrderTranDetails(String ownerEmail,String custEmail);
}
