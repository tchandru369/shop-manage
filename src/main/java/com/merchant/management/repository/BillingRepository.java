package com.merchant.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.merchant.management.entity.BillingEntity;


@EnableJpaRepositories
public interface BillingRepository extends JpaRepository<BillingEntity, Integer>{
	
	@Query(value = "SELECT country_names,state_names,city_names FROM con_state_city", nativeQuery = true)
	List<Object[]> getConStDtls();

}
