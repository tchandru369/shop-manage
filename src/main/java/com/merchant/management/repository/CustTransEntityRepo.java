package com.merchant.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.CustomerTransEntity;

@Repository
@EnableJpaRepositories
public interface CustTransEntityRepo extends JpaRepository<CustomerTransEntity, Integer>{
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_trans_entity SET trans_pymt_order_status = 'BS' WHERE trans_cust_ref_id =:custRef AND trans_own_ref_id =:ownerRef AND trans_cust_order_id =:orderRefId", nativeQuery = true)
	void updtCustPymntOrdList(String ownerRef, String custRef, String orderRefId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE cust_trans_entity SET trans_pymt_order_status = 'RAV' WHERE trans_cust_ref_id =:custRefId AND trans_own_ref_id =:ownerRefId AND trans_cust_order_id =:orderRefId AND trans_pymt_order_status = 'RAP'", nativeQuery = true)
	void updBlnPymtListSuccess(String ownerRefId, String custRefId, String orderRefId);
	
	
	@Query(value = "SELECT * FROM cust_trans_entity WHERE trans_cust_ref_id =:custRefId AND trans_own_ref_id =:ownerRefId AND trans_cust_order_id =:orderRefId AND trans_pymt_ref_id =:pymtRefId", nativeQuery = true)
	CustomerTransEntity getCustTransDetails(String ownerRefId, String custRefId,String orderRefId,String pymtRefId);

	
	
	

}
