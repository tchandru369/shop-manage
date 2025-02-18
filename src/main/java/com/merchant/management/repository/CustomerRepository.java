package com.merchant.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.merchant.management.entity.CustomerDetails;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetails, Integer>{
    
	@Query(value = "SELECT * FROM customer_details ea WHERE ea.customer_phone_no =:customerPhNo", nativeQuery = true)
	CustomerDetails getcustomerDetailsByPhNo(String customerPhNo); 
}
