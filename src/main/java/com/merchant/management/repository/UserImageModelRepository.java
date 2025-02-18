package com.merchant.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.merchant.management.entity.ImageSourceDetails;
import com.merchant.management.entity.ImageSrcDetail;

@Repository
@EnableJpaRepositories
public interface UserImageModelRepository extends JpaRepository<ImageSourceDetails, Long>{

}
