package com.merchant.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.merchant.management.entity.CustTransOrderList;

@Repository
@EnableJpaRepositories
public interface CustTransOrderListRepo extends JpaRepository<CustTransOrderList, Integer>{
	

}
