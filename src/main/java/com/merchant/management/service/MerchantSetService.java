package com.merchant.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.merchant.management.dto.MerchantDetailRes;
import com.merchant.management.entity.ImageModel;
import com.merchant.management.entity.ImageSourceDetails;
import com.merchant.management.entity.ImageSrcDetail;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.repository.CustomerRepository;
import com.merchant.management.repository.ImageRepository;
import com.merchant.management.repository.UserImageModelRepository;

@Service
public class MerchantSetService {
	
	@Autowired
	private UserImageModelRepository userImageRepo;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	public MerchantSetService(UserImageModelRepository userImageRepo,ImageRepository imageRepository) {
		this.userImageRepo = userImageRepo;
		this.imageRepository = imageRepository;
	}
	
	
	public void merchantSaveImage(ImageSourceDetails merchantImgModel) {
		userImageRepo.save(merchantImgModel);
	}
	
	public void updateMerchantImage(ImageModel merchantImgModel) {
		imageRepository.updateMerchantImg(merchantImgModel.getImageByte(), merchantImgModel.getImageType(), merchantImgModel.getImageName(),merchantImgModel.getImageOwnerEmail(), merchantImgModel.getImageStoredModule());
	}
	
	public String getMerchantImageName(String merchantEmail,String merchantImgModule) {
		return imageRepository.getMerchantPhotoName(merchantEmail, merchantImgModule);
	}
	

	public ImageModel getMechantImg(String merchantEmail,String merchantImgModule) {
		ImageModel imageModel = new ImageModel();
		imageModel = imageRepository.getMerchantProfileByModule(merchantEmail, merchantImgModule);
		return imageModel;
	}
	
	

}
