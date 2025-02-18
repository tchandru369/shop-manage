package com.merchant.management.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.merchant.management.dto.MerchantDetailRes;
import com.merchant.management.entity.MerchantDetails;
import com.merchant.management.entity.ImageSrcDetail;

@RestController
@RequestMapping("/services/v1")
@CrossOrigin
public class HomeController {
	
	@GetMapping("/checkAuth")
	public ResponseEntity<MerchantDetailRes> checkAuth(){
		MerchantDetailRes response = new MerchantDetailRes();
		response.setResponse("success");
		return ResponseEntity.ok(response);
	}
	
	// This is to add the products selected in the Add PRoducts in Shop owners
	
//	@PostMapping("/addProducts")
//	public ResponseEntity<MerchantDetailRes> addProducts(@RequestBody ){
//		
//	}
	
	/*
	 * this is to view the products at the time of loading the page UserDashboard
	 * 
	 * */
	
	
     
//	@PostMapping("/viewProducts")
}
