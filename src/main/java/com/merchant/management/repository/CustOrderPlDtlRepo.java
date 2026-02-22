package com.merchant.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.CustOrderPlacedDtls;
import com.merchant.management.entity.OrderRequestDetails;

public interface CustOrderPlDtlRepo extends JpaRepository<CustOrderPlacedDtls, Integer>{

	@Query(value = "SELECT * FROM cust_order_placed_tb WHERE cust_order_owner_ref_id =:ownerRefId AND cust_order_req_status='OP'", nativeQuery = true)
	List<CustOrderPlacedDtls> getCustOrderPLacedDtls(String ownerRefId); 
	
	@Query(value = "SELECT * FROM cust_order_placed_tb WHERE cust_order_crtd_date =:createdDate AND cust_customer_ref_id =:custRefId\r\n"
			+ "	AND cust_order_owner_ref_id =:ownerRefId", nativeQuery = true)
	List<CustOrderPlacedDtls> getCustOrderReqApproved(String ownerRefId,String custRefId,String createdDate);
	
	@Query(value = "SELECT * FROM cust_order_placed_tb WHERE cust_customer_ref_id =:custRefId AND cust_order_owner_ref_id =:ownerRefId AND cust_order_crtd_date <> '' ORDER BY TO_DATE(cust_order_crtd_date, 'DD-MM-YYYY') DESC LIMIT 5", nativeQuery = true)
	List<CustOrderPlacedDtls> getCustOrderReqApprovedRcntDate(String ownerRefId,String custRefId); 
	
	@Query(value = "SELECT COUNT(*) FROM cust_order_placed_tb WHERE cust_customer_ref_id =:custRefId AND cust_order_phno =:custPhNo AND cust_order_crtd_date =:orderDate AND cust_order_ref_id=:orderRefId AND cust_order_owner_ref_id =:ownerRefId AND cust_order_req_status = 'OP';", nativeQuery = true)
	int getCustPlcdOrderCount(String custRefId, String custPhNo, String orderDate,String ownerRefId,String orderRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_placed_tb SET cust_order_req_status ='OPA' WHERE cust_order_owner_ref_id =:ownerRefId\r\n"
			+ "	AND cust_order_crtd_date =:orderedDate AND cust_order_ref_id=:orderRefId AND cust_order_phno =:custPhNo\r\n"
			+ "			AND cust_customer_ref_id =:custRefId AND cust_order_req_status ='OP'", nativeQuery = true)
	void updateCustPlcdOrderStatus(String ownerRefId,String orderRefId,String orderedDate, String custPhNo,String custRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_placed_tb SET cust_order_req_status ='BP' WHERE cust_order_owner_ref_id =:ownerEmail AND cust_order_type =:custType AND cust_order_crtd_date =:orderedDate AND cust_order_phno =:custPhNo AND cust_customer_ref_id =:custRefId AND cust_order_ref_id=:orderRefId AND cust_order_req_status ='OPA'", nativeQuery = true)
	void updateCustPlcdOrderToBp(String ownerEmail,String custType,String orderedDate, String custPhNo,String custRefId,String orderRefId);
	
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_placed_tb SET cust_order_req_status ='BSV',cust_pymt_ref_id =:pymtRefId WHERE cust_order_owner_ref_id =:ownerRefId\r\n"
			+ " AND cust_order_crtd_date =:orderedDate AND cust_order_phno =:custPhNo\r\n"
			+ "	AND cust_customer_ref_id =:custRefId AND cust_order_ref_id =:orderRefId AND cust_order_req_status ='BP'", nativeQuery = true)
	void updateCustPlcdOrderToBSV(String ownerRefId,String orderedDate, String custPhNo,String custRefId,String orderRefId,String pymtRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_placed_tb SET cust_order_req_status ='BS' WHERE cust_order_owner_ref_id =:ownerRefId\r\n"
			+ "	AND cust_order_crtd_date =:orderedDate AND cust_order_ref_id =:orderRefId AND cust_order_phno =:custPhNo AND cust_customer_ref_id =:custRefId\r\n"
			+ "			AND cust_order_req_status ='BSV'", nativeQuery = true)
	void updateCustPlcdOrderToBS(String ownerRefId,String orderRefId,String orderedDate, String custPhNo,String custRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_placed_tb SET cust_order_req_status ='BS',cust_pymt_ref_id =:pymtRefId WHERE cust_order_owner_ref_id =:ownerRefId\r\n"
			+ "			AND cust_order_type =:custType AND cust_order_crtd_date =:orderedDate AND cust_order_ref_id =:orderRefId\r\n"
			+ "			AND cust_customer_ref_id =:custRefId AND cust_order_req_status ='BP'", nativeQuery = true)
	void updateCustPlcdOrderOwnerToBs(String ownerRefId,String custType,String orderedDate, String orderRefId,String custRefId,String pymtRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_placed_tb SET cust_order_req_status ='OPR' WHERE cust_order_owner_ref_id =:ownerEmail\r\n"
			+ "			AND cust_order_type =:custType AND cust_order_crtd_date =:orderedDate AND cust_order_phno =:custPhNo\r\n"
			+ "			AND cust_customer_ref_id =:custRefId AND cust_order_req_status ='OP' AND cust_order_ref_id =:orderRefId", nativeQuery = true)
	void updateCustPlcdOrderStatusR(String ownerEmail,String custType,String orderedDate, String custPhNo,String custRefId,String orderRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_placed_tb SET cust_order_req_status ='OP' WHERE cust_order_owner_ref_id =:ownerEmail\r\n"
			+ "			AND cust_order_type =:custType AND cust_order_crtd_date =:orderedDate AND cust_order_phno =:custPhNo\r\n"
			+ "			AND cust_order_email =:custEmail AND cust_order_req_status ='OPA'", nativeQuery = true)
	void updateCustPlcdStatusBck(String ownerEmail,String custType,String orderedDate, String custPhNo,String custEmail);
	
	@Query(value = "SELECT * FROM cust_order_placed_tb WHERE cust_customer_ref_id =:custEmail AND cust_order_owner_ref_id =:ownerEmail", nativeQuery = true)
	List<CustOrderPlacedDtls> getCustOverallOrderStatus(String custEmail, String ownerEmail);
}
