package com.merchant.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "image_model")
public class ImageModel {
   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "image_name")
	private String imageName;
	@Column(name = "image_type")
	private String imageType;
	@Column(name = "image_owner_email")
	private String imageOwnerEmail;
	@Column(name = "image_store_module")
	private String imageStoredModule;
	@Column(name = "image_byte",columnDefinition = "bytea")
	private byte[] imageByte;
	
	
	public ImageModel() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getImageName() {
		return imageName;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	public String getImageType() {
		return imageType;
	}


	public void setImageType(String imageType) {
		this.imageType = imageType;
	}


	public String getImageOwnerEmail() {
		return imageOwnerEmail;
	}


	public void setImageOwnerEmail(String imageOwnerEmail) {
		this.imageOwnerEmail = imageOwnerEmail;
	}


	public String getImageStoredModule() {
		return imageStoredModule;
	}


	public void setImageStoredModule(String imageStoredModule) {
		this.imageStoredModule = imageStoredModule;
	}


	public byte[] getImageByte() {
		return imageByte;
	}


	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}
}
