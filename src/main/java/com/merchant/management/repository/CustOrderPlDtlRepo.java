package com.merchant.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.CustOrderPlacedDtls;
import com.merchant.management.entity.OrderRequestDetails;

public interface CustOrderPlDtlRepo extends JpaRepository<CustOrderPlacedDtls, Integer>{

	@Query(value = "SELECT * FROM cust_order_placed_tb WHERE cust_order_owner_email =:ownerEmail AND cust_order_req_status='OP' ", nativeQuery = true)
	List<CustOrderPlacedDtls> getCustOrderPLacedDtls(String ownerEmail); 
	
	@Query(value = "SELECT * FROM cust_order_placed_tb WHERE cust_order_crtd_date =:createdDate AND cust_order_email =:custEmail\r\n"
			+ "	AND cust_order_owner_email =:ownerEmail", nativeQuery = true)
	List<CustOrderPlacedDtls> getCustOrderReqApproved(String ownerEmail,String custEmail,String createdDate); 
	
	@Query(value = "SELECT COUNT(*) FROM cust_order_placed_tb WHERE cust_order_email =:custEmail AND cust_order_phno =:custPhNo AND cust_order_crtd_date =:orderDate AND cust_order_type =:custType AND cust_order_owner_email =:ownerEmail AND cust_order_req_status = 'OP';", nativeQuery = true)
	int getCustPlcdOrderCount(String custEmail, String custPhNo, String orderDate, String custType,String ownerEmail);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_placed_tb SET cust_order_req_status ='OPA' WHERE cust_order_owner_email =:ownerEmail\r\n"
			+ "			AND cust_order_type =:custType AND cust_order_crtd_date =:orderedDate AND cust_order_phno =:custPhNo\r\n"
			+ "			AND cust_order_email =:custEmail AND cust_order_req_status ='OP'", nativeQuery = true)
	void updateCustPlcdOrderStatus(String ownerEmail,String custType,String orderedDate, String custPhNo,String custEmail);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_placed_tb SET cust_order_req_status ='OPR' WHERE cust_order_owner_email =:ownerEmail\r\n"
			+ "			AND cust_order_type =:custType AND cust_order_crtd_date =:orderedDate AND cust_order_phno =:custPhNo\r\n"
			+ "			AND cust_order_email =:custEmail AND cust_order_req_status ='OP'", nativeQuery = true)
	void updateCustPlcdOrderStatusR(String ownerEmail,String custType,String orderedDate, String custPhNo,String custEmail);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_placed_tb SET cust_order_req_status ='OP' WHERE cust_order_owner_email =:ownerEmail\r\n"
			+ "			AND cust_order_type =:custType AND cust_order_crtd_date =:orderedDate AND cust_order_phno =:custPhNo\r\n"
			+ "			AND cust_order_email =:custEmail AND cust_order_req_status ='OPA'", nativeQuery = true)
	void updateCustPlcdStatusBck(String ownerEmail,String custType,String orderedDate, String custPhNo,String custEmail);
}
