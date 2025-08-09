package com.merchant.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.CustOrderDtlList;
import com.merchant.management.entity.ShopCustOrderDetails;

public interface CustOrdPlDtlsListRepo extends JpaRepository<CustOrderDtlList, Integer>{
	
	@Query(value = "SELECT * FROM cust_order_dtl_list_tb WHERE cust_order_emailid =:custEmail AND cust_order_owner_name =:ownerEmail\r\n"
			+ " AND cust_order_crd_date =:orderDate AND cust_order_ph_no =:custPhoneNo AND cust_order_req_status = 'OP' AND cust_order_live_flg = '1';", nativeQuery = true)
	List<CustOrderDtlList> getCustOrderPlacedList(String custEmail,String ownerEmail,String orderDate,String custPhoneNo);
	
	@Query(value = "SELECT * FROM cust_order_dtl_list_tb WHERE cust_order_emailid =:custEmail AND cust_order_owner_name =:ownerEmail\r\n"
			+ " AND cust_order_crd_date =:orderDate AND cust_order_req_status =:orderStatus AND cust_order_live_flg = '1';", nativeQuery = true)
	List<CustOrderDtlList> getCustOrderPlacedListAprvd(String custEmail,String ownerEmail,String orderDate,String orderStatus);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_dtl_list_tb SET cust_order_req_status ='OPA'  WHERE cust_order_owner_name =:ownerEmail\r\n"
			+ "			AND cust_order_prod_name =:productName AND cust_order_crd_date =:orderedDate AND cust_order_ph_no =:custPhNo\r\n"
			+ "			AND cust_order_emailid =:custEmail AND cust_order_prod_cmp =:companyName AND cust_order_prod_type =:productType AND cust_order_req_status ='OP'", nativeQuery = true)
	void updateCustPlcdDtlList(String ownerEmail,String orderedDate,String custPhNo, String custEmail,String companyName,String productType,String productName);

	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_dtl_list_tb SET cust_order_req_status ='OPR'  WHERE cust_order_owner_name =:ownerEmail\r\n"
			+ "			AND cust_order_prod_name =:productName AND cust_order_crd_date =:orderedDate AND cust_order_ph_no =:custPhNo\r\n"
			+ "			AND cust_order_emailid =:custEmail AND cust_order_prod_cmp =:companyName AND cust_order_prod_type =:productType AND cust_order_req_status ='OP'", nativeQuery = true)
	void updateCustPlcdDtlListR(String ownerEmail,String orderedDate,String custPhNo, String custEmail,String companyName,String productType,String productName);

	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_dtl_list_tb SET cust_order_req_status ='OP'  WHERE cust_order_owner_name =:ownerEmail\r\n"
			+ "		    AND cust_order_crd_date =:orderedDate AND cust_order_ph_no =:custPhNo\r\n"
			+ "			AND cust_order_emailid =:custEmail AND cust_order_req_status ='OPA'", nativeQuery = true)
	void updateCustPlcdDtlListBck(String ownerEmail,String orderedDate,String custPhNo, String custEmail);
}
