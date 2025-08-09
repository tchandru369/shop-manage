package com.merchant.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.OrderRequestDetails;
import com.merchant.management.entity.OwnerPaymtDetails;

public interface OwnerPaymtDetailRepo extends JpaRepository<OwnerPaymtDetails, Integer>{

	@Query(value = "SELECT COUNT(*) FROM owner_paymt_details WHERE pymt_owner_email =:ownerEmail AND pymt_ph_number=:ownerPh", nativeQuery = true)
	int getPaymtDtlByEmail(String ownerEmail,String ownerPh);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE owner_paymt_details SET pymt_upi_id =:pymtUpi,pymt_mod_on=:modifiedStmp WHERE pymt_owner_email =:ownerEmail AND pymt_ph_number=:ownerPh", nativeQuery = true)
	void updateUPIPymtDetails(String ownerEmail,String pymtUpi,String ownerPh,String modifiedStmp);
	
	@Query(value = "SELECT * FROM owner_paymt_details WHERE pymt_owner_email =:ownerEmail", nativeQuery = true)
	OwnerPaymtDetails getDealerPymtDetails(String ownerEmail);

}
