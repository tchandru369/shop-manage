package com.merchant.management.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.merchant.management.entity.CustomerDetails;
import com.merchant.management.entity.MerchantDetails;

import jakarta.persistence.Id;

@Repository
@EnableJpaRepositories
public interface MerchantRepository extends JpaRepository<MerchantDetails, Long> {
	
	 
	@Query(value = "SELECT ea.merchant_password FROM merchant_details ea WHERE ea.merchant_email =:merchantEmail", nativeQuery = true)
		String findPasswordByEmail(@Param("merchantEmail") String merchantEmail);
	
	@Query(value = "SELECT ea.merchant_name FROM merchant_details ea WHERE ea.merchant_ref_id =:merchantEmail", nativeQuery = true)
	String getMerchantDetails(@Param("merchantEmail") String merchantEmail);
	
	@Query(value = "SELECT * FROM merchant_details ea WHERE ea.merchant_ref_id =:merchantEmail", nativeQuery = true)
	MerchantDetails getMerchantDetailsByRefId(@Param("merchantEmail") String merchantEmail);
	
	@Query(value = "SELECT * FROM merchant_details ea WHERE ea.merchant_email =:ownerRefId", nativeQuery = true)
	MerchantDetails getMerchantProfileDetails(String ownerRefId); 
	
	Optional<MerchantDetails> findBymerchantEmail(String merchantEmail);
	
	@Query(value = "SELECT COUNT(*) FROM merchant_details ea WHERE ea.merchant_email =:merchantEmail", nativeQuery = true)
	int getMerchantCount(String merchantEmail);
	
	
}