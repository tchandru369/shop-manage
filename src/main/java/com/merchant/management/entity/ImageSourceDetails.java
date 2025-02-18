package com.merchant.management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "image_source_details")
public class ImageSourceDetails {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	private Long Id;
	@Column(name = "merchant_img_email")
	private String merchantImgEmail;
	@Column(name = "merchant_img_module")
	private String merchantImgModule;
	@Column(name = "merchant_img_created_date")
	private String merchantImgCreatedDate;
	@Column(name = "merchant_img_modified_date")
	private String merchantImgModifiedDate;
	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "merchant_image",
	 joinColumns = {
			 @JoinColumn(name = "merchant_id")
	 },
	 inverseJoinColumns = {
			 @JoinColumn(name = "image_id")
	 }
			)
	private ImageModel merchantimage;
	
	

	public ImageSourceDetails(Long id, String merchantImgEmail, String merchantImgModule, String merchantImgCreatedDate,
			String merchantImgModifiedDate) {
		super();
		Id = id;
		this.merchantImgEmail = merchantImgEmail;
		this.merchantImgModule = merchantImgModule;
		this.merchantImgCreatedDate = merchantImgCreatedDate;
		this.merchantImgModifiedDate = merchantImgModifiedDate;
	}
	
	public ImageSourceDetails() {
		
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getMerchantImgEmail() {
		return merchantImgEmail;
	}

	public void setMerchantImgEmail(String merchantImgEmail) {
		this.merchantImgEmail = merchantImgEmail;
	}

	public String getMerchantImgModule() {
		return merchantImgModule;
	}

	public void setMerchantImgModule(String merchantImgModule) {
		this.merchantImgModule = merchantImgModule;
	}

	public String getMerchantImgCreatedDate() {
		return merchantImgCreatedDate;
	}

	public void setMerchantImgCreatedDate(String merchantImgCreatedDate) {
		this.merchantImgCreatedDate = merchantImgCreatedDate;
	}

	public String getMerchantImgModifiedDate() {
		return merchantImgModifiedDate;
	}

	public void setMerchantImgModifiedDate(String merchantImgModifiedDate) {
		this.merchantImgModifiedDate = merchantImgModifiedDate;
	}

	public ImageModel getMerchantimage() {
		return merchantimage;
	}

	public void setMerchantimage(ImageModel merchantimage) {
		this.merchantimage = merchantimage;
	}

}
