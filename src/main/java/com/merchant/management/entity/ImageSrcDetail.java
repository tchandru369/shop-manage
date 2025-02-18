package com.merchant.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="merchant_image_model")
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class ImageSrcDetail {
	
	@Id
	@Column(name="image_id")
	@GeneratedValue
	private Long id;
	@Column(name="merchant_image_name") 
	private String merchantImageName;
	@Column(name="merchant_image_type") 
	private String merchantImageType;
	@Column(name="merchant_image_email") 
	private String merchantImageEmail;
	@Column(name="merchant_image_module") 
	private String merchantImageModule;
	public String getMerchantImageModule() {
		return merchantImageModule;
	}
	public void setMerchantImageModule(String merchantImageModule) {
		this.merchantImageModule = merchantImageModule;
	}
	@Lob
	@Column(name="merchant_img_pic")
	private byte[] merchantImgPic;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMerchantImageName() {
		return merchantImageName;
	}
	public void setMerchantImageName(String merchantImageName) {
		this.merchantImageName = merchantImageName;
	}
	public String getMerchantImageType() {
		return merchantImageType;
	}
	public void setMerchantImageType(String merchantImageType) {
		this.merchantImageType = merchantImageType;
	}
	public String getMerchantImageEmail() {
		return merchantImageEmail;
	}
	public void setMerchantImageEmail(String merchantImageEmail) {
		this.merchantImageEmail = merchantImageEmail;
	}
	public byte[] getMerchantImgPic() {
		return merchantImgPic;
	}
	public void setMerchantImgPic(byte[] merchantImgPic) {
		this.merchantImgPic = merchantImgPic;
	}

}
