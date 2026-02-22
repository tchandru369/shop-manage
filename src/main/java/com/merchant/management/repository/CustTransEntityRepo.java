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
	@Query(value = "UPDATE cust_trans_entity SET trans_pymt_order_status = 'BS' WHERE trans_cust_ref_id =:custRef AND trans_own_ref_id =:ownerRef AND trans_pymt_date =:orderedDate", nativeQuery = true)
	void updtCustPymntOrdList(String ownerRef,String orderedDate, String custRef);

	
	
	

}
