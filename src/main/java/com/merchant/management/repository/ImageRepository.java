package com.merchant.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.entity.CustomerDetails;
import com.merchant.management.entity.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
	 
    @Transactional	
    @Query(value = "SELECT * FROM image_model img WHERE img.image_owner_email =:merchantEmail AND img.image_store_module =:merchantImgModule", nativeQuery = true)
	ImageModel getMerchantProfileByModule(String merchantEmail,String merchantImgModule); 
    
    @Modifying
	@Transactional
	@Query(value = "UPDATE image_model SET image_byte =:imgbyte, image_type =:imageType, image_name =:imageName WHERE image_owner_email =:merchantEmail AND image_store_module =:imgModule", nativeQuery = true)
	void updateMerchantImg(byte[] imgbyte,String imageType,String imageName,String merchantEmail,String imgModule);
    
    @Query(value = "SELECT img.image_name FROM image_model img WHERE img.image_owner_email =:merchantEmail AND img.image_store_module =:merchantModule", nativeQuery = true)
	String getMerchantPhotoName( String merchantEmail,String merchantModule);
}
