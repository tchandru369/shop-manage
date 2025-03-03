package com.merchant.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.merchant.management.entity.BillingHistory;
import com.merchant.management.entity.ProductDetails;

@Repository
public interface BillingHistoryRepo extends JpaRepository<BillingHistory, Long>{
	
	@Query(value = "SELECT * FROM billing_history ea WHERE ea.cust_shop_email_id =:merchantEmail", nativeQuery = true)
	List<BillingHistory> getBillingHistoryDetails(String merchantEmail);

}
