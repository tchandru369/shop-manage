package com.merchant.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.merchant.management.entity.BillingHistory;
import com.merchant.management.entity.ProductDetails;

@Repository
@EnableJpaRepositories
public interface BillingHistoryRepo extends JpaRepository<BillingHistory, Long>{
	
	@Query(value = "SELECT * FROM billing_history ea WHERE ea.shop_owner_ref_id =:ownerRefId", nativeQuery = true)
	List<BillingHistory> getBillingHistoryDetails(String ownerRefId);

}
