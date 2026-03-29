package com.merchant.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.BillingHistory;
import com.merchant.management.entity.ProductDetails;

@Repository
@EnableJpaRepositories
public interface BillingHistoryRepo extends JpaRepository<BillingHistory, Long>{
	
	@Query(value = "SELECT * FROM billing_history ea WHERE ea.shop_owner_ref_id =:ownerRefId", nativeQuery = true)
	List<BillingHistory> getBillingHistoryDetails(String ownerRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE billing_history SET cust_due_amt = 0,cust_fully_paid_flg = 'Y',cust_paid_amt=:paidAmt WHERE shop_cust_ref_id=:custRefId AND shop_order_ref_id=:orderRefId AND shop_owner_ref_id=:ownerRefId AND cust_pymt_ref_id =:pymtRefId", nativeQuery = true)
	void updateOwnerOverAllUpdate(double paidAmt,String custRefId,String ownerRefId,String orderRefId,String pymtRefId);

}
