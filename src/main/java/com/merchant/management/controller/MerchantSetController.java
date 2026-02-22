package com.merchant.management.controller;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.merchant.management.dto.MerchantDetailRes;
import com.merchant.management.dto.TransactionDetailsDto;
import com.merchant.management.entity.BillingEntityRes;
import com.merchant.management.entity.CustomerDetails;
import com.merchant.management.entity.CustomerDetailsRes;
import com.merchant.management.entity.ImageModel;
import com.merchant.management.entity.ImageSourceDetails;
import com.merchant.management.entity.ImageSrcDetail;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.entity.MerchantProfileDetails;
import com.merchant.management.entity.OwnerPaymtDetails;
import com.merchant.management.repository.ShopCustomerRepo;
import com.merchant.management.service.MerchantServices;
import com.merchant.management.service.MerchantSetService;


@RestController
@RequestMapping("/services/v1/merchant")
@CrossOrigin
public class MerchantSetController {
  
	
	@Autowired
	private MerchantSetService merchantSetService;
	
	@Autowired
	private MerchantServices merchantServices;
	
	@Autowired
	private ShopCustomerRepo shopCustDtlRepo;
	
	@PostMapping(value = "/saveImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<CustomerDetailsRes> authenticate(@RequestPart("imageDetails") ImageSourceDetails imageModel,
			                                               @RequestPart("imageFile") MultipartFile file) throws IOException{
		CustomerDetailsRes merchantRes = new CustomerDetailsRes();
		ImageModel imageModelUpload = uploadImage(file, imageModel);
		String merchantImageName = merchantSetService.getMerchantImageName(imageModel.getMerchantImgEmail(), imageModel.getMerchantImgModule());
		if(merchantImageName != null) {
			merchantSetService.updateMerchantImage(imageModelUpload);
		}else if(merchantImageName == null){
			imageModel.setMerchantimage(imageModelUpload);
			merchantSetService.merchantSaveImage(imageModel);
		}else {
			merchantRes.setErrorCode("IMG001");
			merchantRes.setErrorMsg("something went wrong while saving...");
			merchantRes.setResponse("failure");
		}
		
		merchantRes.setResponse("success");
		return ResponseEntity.ok(merchantRes);
	}
	
	@GetMapping("/profileDetails")
	public ResponseEntity<MerchantProfileDetails> getProfileDetails(@RequestParam String email){
		MerchantProfileDetails merchantRes = new MerchantProfileDetails();
		MerchantDetails merchantDetails = new MerchantDetails();
		merchantDetails = merchantServices.getMerchantServiceDetails(email);
		merchantRes.setMerchantEmail(shopCustDtlRepo.getCustomerEmailDetails(email));
		merchantRes.setMerchantAddress(merchantDetails.getMerchantAddress());
		merchantRes.setMerchantPhoneNumber(merchantDetails.getMerchantPhoneNumber());
		merchantRes.setMerchantUserName(merchantDetails.getMerchantUserName());
		merchantRes.setMerchantUserType(merchantDetails.getMerchantUserType());
		
		return ResponseEntity.ok(merchantRes);
	}
	
	@GetMapping("/getProfilePic")
	public ResponseEntity getCustomerForBilling(@RequestParam String email,@RequestParam String imgModule){
		CustomerDetailsRes customerDetailsRes = new CustomerDetailsRes();
        ImageModel imageModel = new ImageModel();
        imageModel = merchantSetService.getMechantImg(email, imgModule);
		if(imageModel == null) {
			customerDetailsRes.setErrorCode("ERR001");
			customerDetailsRes.setErrorMsg("Customer Not Found");
			customerDetailsRes.setResponse("failure");
			return ResponseEntity.ok(customerDetailsRes);
		}else {
			return ResponseEntity.ok(imageModel);
		}		
	}
	
	
	public ImageModel uploadImage(MultipartFile multipartFile,ImageSourceDetails imagesorceDtls) throws IOException {
		ImageModel imageModelFile = new ImageModel();
		imageModelFile.setImageOwnerEmail(imagesorceDtls.getMerchantImgEmail());
		imageModelFile.setImageByte(multipartFile.getBytes());
		imageModelFile.setImageName(multipartFile.getOriginalFilename());
		imageModelFile.setImageStoredModule(imagesorceDtls.getMerchantImgModule());
		imageModelFile.setImageType(multipartFile.getContentType());	    
	    return imageModelFile;
	}
	
	@GetMapping("/owner/updatePymt")
	public ResponseEntity updatePaymentDetails(@RequestParam String dealerUpi,@RequestParam String ownerEmail,@RequestParam String ownerName,@RequestParam String ownerPh) {
		BillingEntityRes trans = merchantServices.updatePaymentDetails(dealerUpi, ownerEmail, ownerName, ownerPh);
		return ResponseEntity.ok(trans);
	}
	
	@GetMapping("/cust/owner/pymtDtls")
	public ResponseEntity getDealerPaymtDetails(@RequestParam String ownerEmail) {
		OwnerPaymtDetails trans = merchantServices.getDealerPymtDetails(ownerEmail);
		return ResponseEntity.ok(trans);
	}
	
	
	
	
	
	
	
	
	
	/*
	 * @PostMapping(value = "/saveImage", consumes =
	 * MediaType.MULTIPART_FORM_DATA_VALUE) public ResponseEntity<MerchantDetailRes>
	 * authenticate(@RequestPart("imageDetails") ImageSrcDetail imageModel,
	 * 
	 * @RequestPart("imageFile") MultipartFile file) throws IOException{
	 * System.out.println("Image Model Email : "+imageModel.getMerchantImageEmail())
	 * ; MerchantDetailRes merchantRes = new MerchantDetailRes(); ImageSrcDetail
	 * merchantModel = new ImageSrcDetail();
	 * merchantModel.setMerchantImgPic(file.getBytes());
	 * merchantModel.setMerchantImageName(file.getOriginalFilename());
	 * merchantModel.setMerchantImageType(file.getContentType());
	 * merchantModel.setMerchantImageEmail(imageModel.getMerchantImageEmail()); //
	 * merchantImageModel.setMerchantImageName(merchantImg.getOriginalFilename());
	 * // merchantImageModel.setMerchantImageType(merchantImg.getContentType()); //
	 * //merchantImageModel.setMerchantImageEmail("chan@gmail.com");
	 * System.out.println(merchantModel.getMerchantImgPic());
	 * System.out.println(merchantModel.getMerchantImageName());
	 * System.out.println(merchantModel.getMerchantImageType());
	 * System.out.println(merchantModel.getMerchantImageEmail());
	 * merchantSetService.merchantSaveImage(merchantModel);
	 * merchantRes.setResponse("success"); return ResponseEntity.ok(merchantRes); }
	 */
}
