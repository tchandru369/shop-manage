package com.merchant.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merchant.management.entity.OtpEntity;

public interface OtpRepository extends JpaRepository<OtpEntity, String>{

}
