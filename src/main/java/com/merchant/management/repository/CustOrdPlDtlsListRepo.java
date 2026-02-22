package com.merchant.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.CustOrderDtlList;
import com.merchant.management.entity.ShopCustOrderDetails;

public interface CustOrdPlDtlsListRepo extends JpaRepository<CustOrderDtlList, Integer>{
	
	@Query(value = "SELECT * FROM cust_order_dtl_list_tb WHERE cust_order_crd_date =:orderDate AND cust_order_live_flg = '1' AND cust_order_ref_id =:orderRefId", nativeQuery = true)
	List<CustOrderDtlList> getCustOrderPlacedList(String orderDate,String orderRefId);
	
	@Query(value = "SELECT * FROM cust_order_dtl_list_tb WHERE cust_order_ref_id =:orderRefId\r\n"
			+ " AND cust_order_crd_date =:orderDate AND cust_order_live_flg = '1';", nativeQuery = true)
	List<CustOrderDtlList> getCustOrderPlacedListAprvd(String orderRefId,String orderDate);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_dtl_list_tb SET cust_order_req_status ='OPA'\r\n"
			+ " WHERE cust_order_prod_name =:productName AND cust_order_crd_date =:orderedDate AND cust_order_ref_id =:orderRefId\r\n"
			+ "	AND cust_order_prod_cmp =:companyName AND cust_order_prod_type =:productType", nativeQuery = true)
	void updateCustPlcdDtlList(String orderedDate,String orderRefId,String companyName,String productType,String productName);

	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_dtl_list_tb SET cust_order_req_status ='BP' WHERE cust_order_prod_name =:productName AND cust_order_crd_date =:orderedDate AND cust_order_prod_cmp =:companyName AND cust_order_prod_type =:productType AND cust_order_ref_id =:orderRefId AND cust_order_req_status ='OPA'", nativeQuery = true)
	void updateCustPlcdDtlListBP(String orderedDate,String companyName,String productType,String productName,String orderRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_dtl_list_tb SET cust_order_req_status ='BSV'  WHERE cust_order_ref_id =:orderRefId\r\n"
			+ "			AND cust_order_prod_name =:productName AND cust_order_crd_date =:orderedDate\r\n"
			+ "			AND cust_order_prod_cmp =:companyName AND cust_order_prod_type =:productType AND cust_order_req_status ='BP'", nativeQuery = true)
	void updateCustPlcdDtlListBSV(String orderedDate, String orderRefId,String companyName,String productType,String productName);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_dtl_list_tb SET cust_order_req_status ='BS'  WHERE cust_order_ref_id =:orderRefId\r\n"
			+ " AND cust_order_prod_name =:productName AND cust_order_crd_date =:orderedDate\r\n"
			+ " AND cust_order_prod_cmp =:companyName AND cust_order_prod_type =:productType AND cust_order_req_status ='BSV'", nativeQuery = true)
	void updateCustPlcdDtlListBS(String orderRefId,String orderedDate,String companyName,String productType,String productName);

	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_dtl_list_tb SET cust_order_req_status ='BS'  WHERE cust_order_ref_id =:orderRefId\r\n"
			+ "			AND cust_order_prod_name =:productName AND cust_order_crd_date =:orderedDate\r\n"
			+ "			AND cust_order_prod_cmp =:companyName AND cust_order_prod_type =:productType AND cust_order_req_status ='BP'", nativeQuery = true)
	void updateCustPlcdDtlLstOwnerPaidBS(String orderedDate, String orderRefId,String companyName,String productType,String productName);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_dtl_list_tb SET cust_order_req_status ='OPR'  WHERE cust_order_ref_id =:orderRefId\r\n"
			+ "			AND cust_order_prod_name =:productName AND cust_order_crd_date =:orderedDate\r\n"
			+ "			AND cust_order_prod_cmp =:companyName AND cust_order_prod_type =:productType AND cust_order_req_status ='OP'", nativeQuery = true)
	void updateCustPlcdDtlListR(String orderedDate, String orderRefId,String companyName,String productType,String productName);

	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_order_dtl_list_tb SET cust_order_req_status ='OP' WHERE cust_order_ref_id =:orderRefId\r\n"
			+ " AND cust_order_crd_date =:orderedDate\r\n"
			+ "	AND cust_order_req_status ='OPA'", nativeQuery = true)
	void updateCustPlcdDtlListBck(String orderRefId,String orderedDate);
	
	@Query(value = "SELECT * FROM cust_order_dtl_list_tb WHERE cust_order_ref_id =:orderRefId AND cust_order_crd_date =:orderDate AND cust_order_live_flg = '1'", nativeQuery = true)
	List<CustOrderDtlList> getCustOverAllOrderList(String orderDate,String orderRefId);
}
