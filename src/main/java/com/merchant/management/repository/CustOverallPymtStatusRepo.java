package com.merchant.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.dto.CustOverAllPymtStatusRes;
import com.merchant.management.entity.CustOrderDtlList;
import com.merchant.management.entity.CustOverallPymtStatus;


public interface CustOverallPymtStatusRepo extends JpaRepository<CustOverallPymtStatus, Integer> {
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_overall_pymt_status SET over_all_order_status = 'BS' WHERE cust_ref_id=:custRefId AND bill_date=:billDate AND owner_ref_id=:ownerRefId AND cust_order_ref_id =:orderRefId AND over_all_order_status='BSV'", nativeQuery = true)
	void updateOwnerOverAllUpdate(String custRefId,String billDate,String ownerRefId,String orderRefId);
	
	@Query(value = "SELECT * FROM cust_overall_pymt_status WHERE cust_ref_id=:custRefId AND owner_ref_id=:ownerRefId", nativeQuery = true)
	List<CustOverallPymtStatus> getCustOverAllStatusList(String custRefId,String ownerRefId);
	
	@Query(value = "SELECT COALESCE(p.pymt_date, o.order_date) AS final_date,p.total_amount,p.balance_amount,o.prod_qty FROM (SELECT TO_DATE(pymt_date, 'DD-MM-YYYY') AS pymt_date, SUM(cust_paid_amount) AS total_amount,SUM(pymt_amount_balance) AS balance_amount FROM cust_overall_pymt_status WHERE cust_ref_id =:custRefId AND over_all_order_status = 'BS' GROUP BY TO_DATE(pymt_date, 'DD-MM-YYYY')) p FULL OUTER JOIN (SELECT TO_DATE(order_cust_crtd_date, 'DD-MM-YYYY') AS order_date, SUM(order_cust_prod_qty) AS prod_qty FROM order_details_tb WHERE order_cust_ref_id IN (SELECT order_ref_id FROM order_request_details WHERE order_cust_ref_id =:custRefId AND order_request_status = 'BS') GROUP BY TO_DATE(order_cust_crtd_date, 'DD-MM-YYYY')) o ON p.pymt_date = o.order_date ORDER BY final_date", nativeQuery = true)
	List<Object[]> getCustGraphEntity(String custRefId);

	

}
